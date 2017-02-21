package com.example2.janja.tas_project.Providers;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.Order;

/**
 * Created by JanJa on 04.02.2017.
 */
public interface OrderProvider {


    Order getOrder(int position);

    int getOrderNumber();
}
