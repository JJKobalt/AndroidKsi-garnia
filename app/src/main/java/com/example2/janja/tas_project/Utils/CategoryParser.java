package com.example2.janja.tas_project.Utils;

import android.content.Context;

import com.example2.janja.tas_project.Entity.Category;
import com.example2.janja.tas_project.Providers.NetworkCategoryProvider;
import com.example2.janja.tas_project.listeners.OnCategoriesDownloadedListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JanJa on 03.02.2017.
 */

public class CategoryParser implements OnCategoriesDownloadedListener{

    private NetworkCategoryProvider networkCategoryProvider;
    private HashMap<Integer, String> idToString;
    private static CategoryParser instance = null;



    public static CategoryParser getInstance(Context context) throws IOException, JSONException {
        if (instance == null)
            return new CategoryParser(context);

        else return instance;
  }


    private CategoryParser(Context context) throws IOException, JSONException {
        idToString = new HashMap<>();
         networkCategoryProvider = new NetworkCategoryProvider(context);
        networkCategoryProvider.updateCategories(this);
  }

    private void createIdToStringHashMap(List<Category> categories) {

        for (Category category : categories) {
            idToString.put(category.getId(), category.getCategoryName());

        }
    }



   public String parseCategoryIdToString(int id)
    {
        return idToString.get(id);
     }


    @Override
    public void onCategoriesDownloaded() {
        createIdToStringHashMap(networkCategoryProvider.getCategories());
    }
}
