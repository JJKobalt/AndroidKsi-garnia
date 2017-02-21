package com.example2.janja.tas_project.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JanJa on 03.02.2017.
 */

public class User implements Serializable {

    private long id;
    private String name;
    private String surname;
    private String login;
    private Address address;
    private List<Order> orders;


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public Address getAddress() {
        return address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public long getId() {
        return id;
    }

    public User(long id, String name, String surname, String login, Address address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.address = address;
        this.orders = orders;
    }
}
