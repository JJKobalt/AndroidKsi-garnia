package com.example2.janja.tas_project;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example2.janja.tas_project.Providers.NetworkBookProvider;
import com.example2.janja.tas_project.Service.UserService;
import com.example2.janja.tas_project.Utils.AddressParser;
import com.example2.janja.tas_project.Utils.CategoryParser;
import com.example2.janja.tas_project.listeners.OnBookDownloadedListener;
import com.example2.janja.tas_project.listeners.UserServiceListener;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example2.janja.tas_project", appContext.getPackageName());
    }

    class MockOnBookDownloadedListener implements OnBookDownloadedListener {


        @Override
        public void onBookDownloaded() {
            System.out.println("listener onBookDownloaded");
        }
    }


    @Test
    public void isBookDownloadWorking() throws Exception {


        Context context = InstrumentationRegistry.getTargetContext();

        NetworkBookProvider networkBookProvider = new NetworkBookProvider(context);


        MockOnBookDownloadedListener mockOnBookDownloadedListener = new MockOnBookDownloadedListener();


        networkBookProvider.updateBookList(mockOnBookDownloadedListener);


        assertTrue(networkBookProvider.getBookList().size() > 1);


    }

    @Test
    public void isCategoryParserWorking() throws Exception {

        Context context = InstrumentationRegistry.getTargetContext();

        CategoryParser categoryParser = CategoryParser.getInstance(context);


        assertTrue(Objects.equals("fantastyka", categoryParser.parseCategoryIdToString(1)));
        assertEquals("fantastyka", categoryParser.parseCategoryIdToString(1));
        assertEquals("horror", categoryParser.parseCategoryIdToString(5));
    }

    @Test
    public void isUserServiceWorking() throws Exception {
        Log.v("test", " test");
        Context context = InstrumentationRegistry.getTargetContext();

        class UserServiceListenerObj implements UserServiceListener {

            @Override
            public void loginSuccesful() {
                Log.v("User_Service", "Succes");
            }

            @Override
            public void loginError() {
                Log.v("User_Service", "Failure");
            }
        }

        UserService userService = new UserService(context, new UserServiceListenerObj());
        userService.tryLogin("aacha", "aacha");
        assertNotNull(userService.getUser());
    }

    @Test
    public void isUserServiceWrongLoginWorking() throws Exception {
        Log.v("test", " test");
        Context context = InstrumentationRegistry.getTargetContext();

        class UserServiceListenerObj implements UserServiceListener {

            @Override
            public void loginSuccesful() {
                Log.v("User_Service", "Succes");
            }

            @Override
            public void loginError() {
                Log.v("User_Service", "Failure");
            }
        }

        UserService userService = new UserService(context, new UserServiceListenerObj());
        userService.tryLogin("wrongUser", "wrongPassword");
        assertNull(userService.getUser());
    }



    @Test
    public void isAddresServiceWorking() throws Exception {

        Context context = InstrumentationRegistry.getTargetContext();

        AddressParser addressParser = new AddressParser(context);
        assertNotNull(addressParser.getAddressFromAddressId(1));


    }


}