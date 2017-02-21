package com.example2.janja.tas_project.Providers;

import android.content.Context;
import android.widget.Toast;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.Category;
import com.example2.janja.tas_project.HttpNameAssistant;
import com.example2.janja.tas_project.NetworkRequest;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.listeners.OnCategoriesDownloadedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanJa on 02.02.2017.
 */

public class NetworkCategoryProvider implements CategoryProvider{

    List<Category> categories = new ArrayList<>();
    private final Context context;
    private static final String CATEGORIES_URL = HttpNameAssistant.LOCALHOST.getMethod() +"/categories";


    public NetworkCategoryProvider(Context context) throws IOException, JSONException {

        this.context = context;

    }



    public void updateCategories(OnCategoriesDownloadedListener listener) throws JSONException, IOException {
        if (NetworkRequest.isOnline(context)) {
            String s = downloadCategories();


            JSONArray categoryArray = new JSONArray(s);

            for (int i = 0; i < categoryArray.length(); ++i) {
                JSONObject recipeObject = categoryArray.getJSONObject(i);

                Category category = new Category(
                        recipeObject.getString("categoryName"),
                        recipeObject.getInt("id")
                        );



                        categories.add(category);
            }

            listener.onCategoriesDownloaded();
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }


    }

    private String downloadCategories() throws IOException {
        return new NetworkRequest(CATEGORIES_URL, HttpNameAssistant.GET).execute();
    }


    public List<Category> getCategories() {
        return categories;
    }


    @Override
    public Category getCategory(int position) {
        return  categories.get(position);
    }

    @Override
    public int getCategoryNumber() {
        return categories.size();
    }
}
