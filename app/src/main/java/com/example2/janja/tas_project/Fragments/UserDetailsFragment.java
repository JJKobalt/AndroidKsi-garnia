package com.example2.janja.tas_project.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example2.janja.tas_project.Entity.User;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.Service.UserService;
import com.example2.janja.tas_project.listeners.OnFragmentInteractionListener;


public class UserDetailsFragment extends Fragment {

    private static final String ARG_User = "USER";

    private User user;


    private OnFragmentInteractionListener mListener;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    public static UserDetailsFragment newInstance(User user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_User, user);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User)getArguments().getSerializable(ARG_User);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_user_details, container, false);

        initializeView(view);

        return view;
    }

    private void initializeView(View view) {

        ((TextView)view.findViewById(R.id.user_login)).setText(user.getLogin());
        ((TextView)view.findViewById(R.id.user_name)).setText(user.getName());
        ((TextView)view.findViewById(R.id.user_surname)).setText(user.getSurname());

         Fragment fragment =OrderFragment.newInstance(1,user.getId());

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.orders_layout, fragment, "Orders_Fragment").commit();

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
