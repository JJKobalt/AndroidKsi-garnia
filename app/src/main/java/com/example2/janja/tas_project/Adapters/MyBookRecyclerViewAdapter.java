package com.example2.janja.tas_project.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.listeners.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;



public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> {

    List<Book> bookList = new ArrayList<>();

    private final OnListFragmentInteractionListener mListener;

    public MyBookRecyclerViewAdapter(OnListFragmentInteractionListener mListener) {

        this.mListener = mListener;
    }


    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.setBook(bookList.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.book);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView bookname;
        public final TextView authors;
        public final TextView category;
        public final TextView price;
        public Book book;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            bookname = (TextView) view.findViewById(R.id.title);
            authors = (TextView) view.findViewById(R.id.authors);
            category = (TextView) view.findViewById(R.id.category);
            price = (TextView) view.findViewById(R.id.price);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + bookname.getText() + "'";
        }

        public void setBook(Book book) {

            this.book = book;
            bookname.setText(book.getTitle());
            authors.setText(book.getAuthors());
            category.setText(book.getCategory());
            price.setText(book.getPrice());


        }
    }
}
