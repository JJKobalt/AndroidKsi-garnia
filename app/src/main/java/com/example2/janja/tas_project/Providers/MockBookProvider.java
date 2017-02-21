package com.example2.janja.tas_project.Providers;

import com.example2.janja.tas_project.Entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanJa on 30.01.2017.
 */

public class MockBookProvider implements BookProvider{

    List<Book> bookList = new ArrayList<>();

    public MockBookProvider() {
    for(int i=0;i<15;i++)
    {
        bookList.add(generateSimpleBookData(i));
    }

    }

    Book generateSimpleBookData(int i)
    {
         String title = "title " + i;
         String authors= "authors " + i;
         String description= "description " + i;
         String category= "category " + i;
         String price= "price " + i;

      return    new Book(i,title,authors,description,category,price);

    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public Book getBook(int position) {
        return bookList.get(position);
    }

    @Override
    public int getBookNumber() {
        return bookList.size();
    }
}
