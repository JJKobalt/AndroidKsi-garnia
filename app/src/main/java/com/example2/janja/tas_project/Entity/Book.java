package com.example2.janja.tas_project.Entity;

import java.io.Serializable;

/**
 * Created by JanJa on 30.01.2017.
 */

public class Book implements Serializable {
    private long id;
    private String title;
    private String authors;
    private String description;
    private String category;
    private String price;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public Book(long id,String title, String authors, String description, String category, String price) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.category = category;
        this.price = price;
    }
}
