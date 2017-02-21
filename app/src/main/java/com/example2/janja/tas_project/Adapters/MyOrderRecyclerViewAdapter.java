package com.example2.janja.tas_project.Adapters;

import android.support.v7.widget.RecyclerView;

import com.example2.janja.tas_project.Entity.Order;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.listeners.OnListFragmentInteractionListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;


public class MyOrderRecyclerViewAdapter extends RecyclerView.Adapter<MyOrderRecyclerViewAdapter.ViewHolder> {

    private List<Order> orders = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public MyOrderRecyclerViewAdapter(OnListFragmentInteractionListener listener) {

        mListener = listener;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.setOrder(orders.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.order);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView order_date;
        public final TextView order_book;
        public Order order;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            order_date = (TextView) view.findViewById(R.id.order_date);
            order_book = (TextView) view.findViewById(R.id.order_book);

        }

        public void setOrder(Order order) {
            this.order = order;

            order_date.setText(order.getDate());
            order_book.setText(String.valueOf(order.getBook().getId()));

        }


        @Override
        public String toString() {
            return super.toString() + " '" + order_book.getText() + "'";
        }
    }
}
