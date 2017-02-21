package com.example2.janja.tas_project.Utils;

import android.content.Context;

import com.example2.janja.tas_project.Entity.Address;
import com.example2.janja.tas_project.Service.AddressService;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by JanJa on 04.02.2017.
 */

public class AddressParser {


    private AddressService addressService;


    public AddressParser(Context context) {
        this.addressService = new AddressService(context);

    }

    public Address getAddressFromAddressId(long addressId) throws IOException, JSONException {

            return addressService.getAddressFromAddressId(addressId);

    }
}
