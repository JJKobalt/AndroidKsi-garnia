package com.example2.janja.tas_project.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example2.janja.tas_project.Adapters.MyOrderRecyclerViewAdapter;
import com.example2.janja.tas_project.Providers.MockOrderProvider;

import com.example2.janja.tas_project.Providers.NetworkOrdersProvider;
import com.example2.janja.tas_project.Providers.OrderProvider;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.listeners.OnListFragmentInteractionListener;
import com.example2.janja.tas_project.listeners.OnOrdersDownloadListener;

import org.json.JSONException;

import java.io.IOException;


public class OrderFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_USER_ID = "USER_ID";

    private int mColumnCount = 1;
    long userId;
    private OnListFragmentInteractionListener mListener;
    Handler handler;




    public OrderFragment() {
    }


    public static OrderFragment newInstance(int columnCount,long userId) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putLong(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            userId=getArguments().getLong(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            RecyclerView.Adapter adapter = new MyOrderRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);
            downloadOrdersInThread(context, adapter);



        }
        return view;
    }

    private void downloadOrdersInThread(final Context context, final RecyclerView.Adapter adapter) {
              new Thread(new Runnable() {

            @Override
            public void run() {
                downloadOrders(context, adapter);
            }
        }).start();
    }


    public void downloadOrders(Context context, RecyclerView.Adapter adapter) {
        try {
            fetchOrders(context, adapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fetchOrders(Context context, final RecyclerView.Adapter adapter) throws IOException, JSONException {
final NetworkOrdersProvider ordersProvider = new NetworkOrdersProvider(context);
        ordersProvider.updateOrderListOfUser(new OnOrdersDownloadListener() {
            @Override
            public void odOrderDownloaded() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        MockOrderProvider mockOrderProvider = new MockOrderProvider();

                        ((MyOrderRecyclerViewAdapter) adapter).setOrders(ordersProvider.getOrders());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        },userId);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    private void showToast(final int resId, final Context context) {
        handler.post(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
