package com.example2.janja.tas_project.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.User;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.Utils.Sender;
import com.example2.janja.tas_project.listeners.OnFragmentInteractionListener;


import java.io.IOException;


public class BookDetailsFragment extends Fragment {

    private static final String ARG_BOOK = "BOOK";
    private static final String ARG_USER = "USER";


    private Book book;
    private User user;


    private OnFragmentInteractionListener mListener;

    public BookDetailsFragment() {
        // Required empty public constructor
    }


    public static BookDetailsFragment newInstance(Book book, User user) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK, book);
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (Book) getArguments().get(ARG_BOOK);
            user = (User) getArguments().get(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_details, container, false);

        ((TextView) view.findViewById(R.id.book_title)).setText(book.getTitle());
        ((TextView) view.findViewById(R.id.book_authors)).setText(book.getAuthors());
        ((TextView) view.findViewById(R.id.book_category)).setText(book.getCategory());
        ((TextView) view.findViewById(R.id.book_description)).setText(book.getDescription());
        ((TextView) view.findViewById(R.id.book_Price)).setText(book.getPrice());


        ((Button) view.findViewById(R.id.buy_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user == null || book == null) {
                    Toast.makeText(getActivity(), getActivity().getString(R.string.not_logged), Toast.LENGTH_LONG).show();
                    return;
                }



                try {
                    Sender.makeRequest(user.getId(),book.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                Toast.makeText(getActivity(), getActivity().getString(R.string.book_bought), Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
