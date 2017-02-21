package com.example2.janja.tas_project.Providers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.Order;
import com.example2.janja.tas_project.HttpNameAssistant;
import com.example2.janja.tas_project.NetworkRequest;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.Utils.BookParser;
import com.example2.janja.tas_project.listeners.OnOrdersDownloadListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanJa on 05.02.2017.
 */

public class NetworkOrdersProvider implements OrderProvider {

    List<Order> orders = new ArrayList<>();
    private final Context context;
    private static final String BOOK_URL = HttpNameAssistant.LOCALHOST.getMethod() + "/orders";

    public NetworkOrdersProvider(Context context) {
        this.context = context;
    }


    public void updateOrderListOfUser(OnOrdersDownloadListener listener, long userId) throws IOException, JSONException {
        if (NetworkRequest.isOnline(context)) {
            String s = downloadOrdersOfUser(userId);

            BookParser bookParser = new BookParser();

            JSONArray orderArray = new JSONArray(s);

            for (int i = 0; i < orderArray.length(); ++i) {
                JSONObject orderObject = orderArray.getJSONObject(i);

                Order order = new Order(orderObject.getLong("id"), orderObject.getString("date"),
                        orderObject.getString("orderStateId"),
                        bookParser.bookIdToBookDummy(orderObject.getLong("numberOfOrdered"))

                );

                orders.add(order);
            }

            listener.odOrderDownloaded();


        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
    }

    private String downloadOrdersOfUser(long userId) throws IOException {
        Log.v("Orders", " " + BOOK_URL + "/show/" + userId);
        return new NetworkRequest(BOOK_URL + "/show/" + userId, HttpNameAssistant.GET).execute();
    }


    public List<Order> getOrders() {
        return orders;
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
