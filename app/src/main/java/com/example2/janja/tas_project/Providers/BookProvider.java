package com.example2.janja.tas_project.Providers;

import com.example2.janja.tas_project.Entity.Book;

/**
 * Created by JanJa on 30.01.2017.
 */

public interface BookProvider {

    Book getBook(int position);

    int getBookNumber();


}
