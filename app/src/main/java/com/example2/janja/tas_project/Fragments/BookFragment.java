package com.example2.janja.tas_project.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example2.janja.tas_project.Adapters.MyBookRecyclerViewAdapter;
import com.example2.janja.tas_project.MainActivity;
import com.example2.janja.tas_project.Providers.BookProvider;
import com.example2.janja.tas_project.Providers.NetworkBookProvider;
import com.example2.janja.tas_project.listeners.OnBookDownloadedListener;
import com.example2.janja.tas_project.listeners.OnListFragmentInteractionListener;
import com.example2.janja.tas_project.R;

import org.json.JSONException;

import java.io.IOException;


public class BookFragment extends Fragment{


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    Handler handler;

    public BookProvider networkBookProvider;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BookFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BookFragment newInstance(int columnCount) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            RecyclerView.Adapter adapter = new MyBookRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);

            DownloadBooksInThread(context,adapter);

        }
        return view;
    }

    private void DownloadBooksInThread(final Context context, final RecyclerView.Adapter adapter) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                downloadBooks(context,adapter);
            }
        }).start();

    }


    public void downloadBooks(Context context,RecyclerView.Adapter adapter){
        try {
            fetchBooks(context,adapter);
        } catch (IOException e) {
            Log.e("BookFragment", "IOException while fetching books", e);
            showToast(R.string.io_exception, context);
        } catch (JSONException e) {
            Log.e("BookFragment", "JSONException while fetching books", e);
            showToast(R.string.server_exception,context);
        }



    }

    private void showToast(final int resId , final Context context) {
        handler.post(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchBooks(Context context, final RecyclerView.Adapter adapter) throws IOException, JSONException {
        final NetworkBookProvider networkBookProvider = new NetworkBookProvider(context);

        networkBookProvider.updateBookList(new OnBookDownloadedListener() {

            @Override
            public void onBookDownloaded() {


                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Log.d("Book Download", "Fetched " + networkBookProvider.getBookList().size() + " books");
                        ((MyBookRecyclerViewAdapter)adapter).setBookList(networkBookProvider.getBookList());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


   public void setLoadingView()
   {
       ((MainActivity)mListener).setLoadingView();


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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
