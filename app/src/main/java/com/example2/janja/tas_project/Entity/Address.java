package com.example2.janja.tas_project.Entity;

import java.io.Serializable;

/**
 * Created by JanJa on 03.02.2017.
 */

public class Address implements Serializable{
    private String postalcode;
    private String streetname;
    private String cityname;

    public String getPostalcode() {
        return postalcode;
    }

    public String getStreetname() {
        return streetname;
    }

    public String getCityname() {
        return cityname;
    }

    public Address(String postalcode, String streetname, String cityname) {

        this.postalcode = postalcode;
        this.streetname = streetname;
        this.cityname = cityname;
    }
}
