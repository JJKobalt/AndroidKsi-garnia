package com.example2.janja.tas_project.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example2.janja.tas_project.Entity.User;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.listeners.OnFragmentInteractionListener;
import com.example2.janja.tas_project.listeners.UserServiceListener;

public class UserFragment extends Fragment implements UserServiceListener {

    private static final String ARG_USER = "USER";
    User user;
    private OnFragmentInteractionListener mListener;

    public UserFragment() {
        // Required empty public constructor
    }



    public static UserFragment newInstance(User user) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        view.findViewById(R.id.user_frame);


        if (user == null) setLoginView();
        else setUserDetailsView(user);

        return view;


    }

    private void setLoginView() {
        android.app.Fragment fragment = UserLoginFragment.newInstance(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.user_frame, fragment, "Login_Fragment").commit();


    }

    private void setUserDetailsView(User user) {
        android.app.Fragment fragment = UserDetailsFragment.newInstance(user);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.user_frame, fragment, "User_Detail_Fragment").commit();


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

    @Override
    public void loginSuccesful(User user) {

        Log.v("Login", "Succes");
        this.user = user;
        mListener.userChanged(user);
        setUserDetailsView(user);
    }

    @Override
    public void loginError() {
        Log.v("Login", "Failure");
        setLoginView();
    }


}
