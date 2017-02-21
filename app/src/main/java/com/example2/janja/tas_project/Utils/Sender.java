package com.example2.janja.tas_project.Utils;




import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.net.HttpURLConnection;

import java.net.*;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;


/**
 * Created by JanJa on 05.02.2017.
 */

public class Sender {

    public static void execute() {
        Map<String, String> comment = new HashMap<String, String>();
        comment.put("subject", "Using the GSON library");
        comment.put("message", "Using libraries is convenient.");
        String json = new GsonBuilder().create().toJson(comment, Map.class);
        makeRequest("http://192.168.0.1:3000/post/77/comments", json);
    }

    public static HttpResponse makeRequest(String uri, String json) {
        try {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            return new DefaultHttpClient().execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void makeRequest(long userID, long bookId) throws Exception
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
         HttpPost httpost = new HttpPost("http://10.0.2.2:8080/orders");

        JSONObject holder= new JSONObject(); ;
        holder.put("id", -1);
        String pattern = "dd-MM-yyyy";

        holder.put("date", new SimpleDateFormat(pattern).format(new Date()));
        holder.put("userId", userID);
        holder.put("numberOfOrdered", bookId);
        holder.put("orderStateId", "1");

        StringEntity se = new StringEntity(holder.toString());

        httpost.setEntity(se);
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");
        ResponseHandler responseHandler = new BasicResponseHandler();
          httpclient.execute(httpost, responseHandler);
    }



}
