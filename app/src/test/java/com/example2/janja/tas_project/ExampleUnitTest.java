package com.example2.janja.tas_project;

import android.content.Context;
import android.test.mock.MockContext;

import com.example2.janja.tas_project.Providers.NetworkBookProvider;
import com.example2.janja.tas_project.listeners.OnBookDownloadedListener;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

class MockOnBookDownloadedListener implements OnBookDownloadedListener {


    @Override
    public void onBookDownloaded() {
        System.out.println("listener onBookDownloaded");
    }
}


public class ExampleUnitTest {
    @Test
    public void isBookDownloadWorking() throws Exception {
        Context context = new MockContext();
        NetworkBookProvider networkBookProvider = new NetworkBookProvider(context);


        MockOnBookDownloadedListener mockOnBookDownloadedListener = new MockOnBookDownloadedListener();


        networkBookProvider.updateBookList(mockOnBookDownloadedListener);


    }
}