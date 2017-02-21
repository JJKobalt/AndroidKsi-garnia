package com.example2.janja.tas_project;

/**
 * Created by JanJa on 01.02.2017.
 */

public enum HttpNameAssistant {
    GET("GET"), POST("POST") , LOCALHOST("http://10.0.2.2:8080");

    private final String method;


    HttpNameAssistant(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
