package com.example2.janja.tas_project.Entity;

/**
 * Created by JanJa on 02.02.2017.
 */

public class Category {
    private int id;
    private String categoryName;

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category(String categoryName, int id) {

        this.categoryName = categoryName;
        this.id = id;
    }
}
