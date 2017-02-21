package com.example2.janja.tas_project.Service;

import android.content.Context;

import android.widget.Toast;

import com.example2.janja.tas_project.Entity.Address;
import com.example2.janja.tas_project.HttpNameAssistant;
import com.example2.janja.tas_project.NetworkRequest;
import com.example2.janja.tas_project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by JanJa on 04.02.2017.
 */
public class AddressService {


    private String localhost = HttpNameAssistant.LOCALHOST.getMethod();

    private final Context context;

    public AddressService(Context context) {
        this.context = context;
    }


    public Address getAddressFromAddressId(long addressId) throws IOException, JSONException {
        String webAddress = localhost + "/addresses/" + addressId;


        if (NetworkRequest.isOnline(context)) {
            String s = getAddress(webAddress);
            if (s == null || s.equals("")) ;
            else {
                return readAddressData(s);

            }


        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private Address readAddressData(String s) throws JSONException {

       // JSONArray addressArray = new JSONArray(s);


        JSONObject addressObject = new JSONObject(s);

        return new Address(addressObject.getString("postalcode"), addressObject.getString("streetName"), addressObject.getString("cityName"));


    }

    private String getAddress(String webAddress) throws IOException {
        return new NetworkRequest(webAddress, HttpNameAssistant.GET).execute();
    }

}
