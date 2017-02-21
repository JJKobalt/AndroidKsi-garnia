package com.example2.janja.tas_project.Service;

import android.content.Context;
import android.widget.Toast;

import com.example2.janja.tas_project.Entity.User;
import com.example2.janja.tas_project.HttpNameAssistant;
import com.example2.janja.tas_project.NetworkRequest;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.Utils.AddressParser;
import com.example2.janja.tas_project.listeners.UserServiceListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by JanJa on 04.02.2017.
 */

public class UserService {

    private final Context context;
    public UserServiceListener listener;
    private String localhost = HttpNameAssistant.LOCALHOST.getMethod();
    User user=null;


    public UserService(Context context, UserServiceListener listener) {

        this.context = context;
        this.listener = listener;
    }

    public void tryLogin(String login, String password) throws IOException, JSONException {
        String webAdress = localhost + "/users/login?login=" + login + "&password=" + password;


        if (NetworkRequest.isOnline(context)) {
            String s = getUser(webAdress);
            if (s == null || s.equals("")) listener.loginError();
            else {
                user = readUserData(s);
                listener.loginSuccesful(user);
            }


        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

    }

    private User readUserData(String s) throws JSONException, IOException {

        AddressParser addressParser = new AddressParser(context);


        JSONObject userObject = new JSONObject(s);

        return new User(userObject.getInt("id"),
                userObject.getString("name"),
                userObject.getString("surname"),
                userObject.getString("login"),
                addressParser.getAddressFromAddressId(userObject.getLong("addressId")),
                null
        );


    }


    public User getUser() {
        return user;


    }

    private String getUser(String webAdress) throws IOException {
        return new NetworkRequest(webAdress, HttpNameAssistant.GET).execute();
    }


}
