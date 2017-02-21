package com.example2.janja.tas_project.Entity;

/**
 * Created by JanJa on 03.02.2017.
 */

public class Order {
    long id;
    String date;
    String OrderState;
    Book book;

    public Book getBook() {
        return book;
    }

    public String getOrderState() {
        return OrderState;
    }

    public String getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public Order(long id, String date, String orderState, Book book) {
this.id = id;

        this.date = date;
        OrderState = orderState;
        this.book = book;
    }
}
