package com.example2.janja.tas_project.Utils;

import com.example2.janja.tas_project.Entity.Book;

/**
 * Created by JanJa on 05.02.2017.
 */

public class BookParser {



    public Book bookIdToBookDummy(long id)
    {
        return  new  Book(id,
                "title of "+id,
                "author of "+id,
                "description of "+id,
                "category of "+id,
                "price of "+id

        )
        ;
    }

}
