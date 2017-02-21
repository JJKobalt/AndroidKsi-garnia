package com.example2.janja.tas_project.Providers;

import android.content.Context;

import android.widget.Toast;

import com.example2.janja.tas_project.Entity.Book;

import com.example2.janja.tas_project.HttpNameAssistant;
import com.example2.janja.tas_project.NetworkRequest;
import com.example2.janja.tas_project.R;
import com.example2.janja.tas_project.Utils.CategoryParser;
import com.example2.janja.tas_project.listeners.OnBookDownloadedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanJa on 01.02.2017.
 */

public class NetworkBookProvider implements BookProvider {

    List<Book> bookList = new ArrayList<>();
    private final Context context;
    private static final String BOOK_URL = HttpNameAssistant.LOCALHOST.getMethod() + "/books";


    public NetworkBookProvider(Context context) throws IOException, JSONException {

        this.context = context;

    }



    public void updateBookList(OnBookDownloadedListener listener) throws JSONException, IOException {
        if (NetworkRequest.isOnline(context)) {
            String s = downloadBooks();
            CategoryParser categoryParser = CategoryParser.getInstance(context);


            JSONArray bookArray = new JSONArray(s);

            for (int i = 0; i < bookArray.length(); ++i) {
                JSONObject bookObject = bookArray.getJSONObject(i);

                Book book = new Book(bookObject.getLong("id"),bookObject.getString("title"),
                        bookObject.getString("authors"),
                        bookObject.getString("description"),
                        categoryParser.parseCategoryIdToString( bookObject.getInt("categoryid")),
                        bookObject.getString("price"));

                bookList.add(book);
            }

            listener.onBookDownloaded();
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show();
       }


    }

    private String downloadBooks() throws IOException {
        return new NetworkRequest(BOOK_URL, HttpNameAssistant.GET).execute();
    }


    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public Book getBook(int position) {
        return getBookList().get(position);
    }

    @Override
    public int getBookNumber() {
        return getBookList().size();
    }



}
