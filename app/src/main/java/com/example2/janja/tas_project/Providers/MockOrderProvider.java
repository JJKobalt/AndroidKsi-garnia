package com.example2.janja.tas_project.Providers;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanJa on 04.02.2017.
 */

public class MockOrderProvider implements OrderProvider{

    List<Order> orders = new ArrayList<>();


    public MockOrderProvider() {
        for (int i = 0; i < 15; i++) {
            orders.add(generateFalseOrder(i));


        }


    }

    public List<Order> getOrders() {
        return orders;
    }

    private Order generateFalseOrder(int i) {

        String date = "date " + i;
        String orderState = "order State" + i;
        Book book = new Book(0, "title", "authors", "description", "category", "price");
        return new Order(i,date, orderState, book);

    }

    @Override
    public Order getOrder(int position) {
        return orders.get(position);
    }

    @Override
    public int getOrderNumber() {
        return orders.size();
    }
}
