package com.example2.janja.tas_project.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.Service.UserService;
import com.example2.janja.tas_project.listeners.OnFragmentInteractionListener;
import com.example2.janja.tas_project.listeners.UserServiceListener;

import org.json.JSONException;

import java.io.IOException;


public class UserLoginFragment extends Fragment {


    UserService userService;
    private static final String ARG_PARENT = "PARENT";
    private OnFragmentInteractionListener mListener;
    private UserServiceListener userServiceListener;
    EditText loginEditText;
    EditText passwordEditText;



    public UserLoginFragment() {
        // Required empty public constructor
    }


    public static UserLoginFragment newInstance(UserServiceListener listener) {
        UserLoginFragment fragment = new UserLoginFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARENT, listener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userServiceListener = (UserServiceListener) getArguments().get(ARG_PARENT);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        initializeView(view);

        return view;
    }

    private void initializeView(View view) {

        loginEditText = ((EditText) view.findViewById(R.id.NameEditText));
        passwordEditText = (EditText) view.findViewById(R.id.PasswordEditText);

        ((Button) view.findViewById(R.id.LoginButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = getActivity();
                 userService = new UserService(context, userServiceListener);


                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        HandleTryLogin();
                    }
                }).start();
           }
        });


    }

    private void HandleTryLogin() {
        try {
            tryLogin();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tryLogin() throws IOException, JSONException {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        userService.tryLogin(login,password);
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
