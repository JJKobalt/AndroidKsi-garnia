package com.example2.janja.tas_project.Providers;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.Category;

/**
 * Created by JanJa on 02.02.2017.
 */

public interface CategoryProvider {


    Category getCategory(int position);

    int getCategoryNumber();


}
