package com.example2.janja.tas_project;

import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example2.janja.tas_project.Entity.Book;
import com.example2.janja.tas_project.Entity.Order;
import com.example2.janja.tas_project.Entity.User;
import com.example2.janja.tas_project.Fragments.BookDetailsFragment;
import com.example2.janja.tas_project.Fragments.BookFragment;
import com.example2.janja.tas_project.Fragments.UserFragment;
import com.example2.janja.tas_project.Fragments.WelcomeFragment;
import com.example2.janja.tas_project.listeners.OnFragmentInteractionListener;
import com.example2.janja.tas_project.listeners.OnListFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, OnListFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private String[] pageNames;


    User currentUser=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPageNames();

        setDrawer(savedInstanceState);
        if (savedInstanceState == null) {
            setWelcomeView();
        }

    }

    private void setDrawer(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_item, pageNames));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }


        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    @Override
    public void onListFragmentInteraction(Book book) {
        setSelectedBookView(book);
    }

    @Override
    public void onListFragmentInteraction(Order order) {

    }


    class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }


        private void selectItem(int position) {


            Fragment fragment = createFragmentOfIndex(position);

            if(isRepetitive(fragment))
            {
                mDrawerLayout.closeDrawer(mDrawerList);
                return;
            };



            Bundle args = new Bundle();

            FragmentManager fragmentManager = getFragmentManager();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment, "BOOK_LIST");


            transaction.addToBackStack(fragment.getClass().getSimpleName());

            transaction.commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);

            mDrawerLayout.closeDrawer(mDrawerList);
        }


    }

    private boolean isRepetitive(Fragment fragment) {
        Fragment currentFragment = getCurrentFragment();

        if (currentFragment != null) {
            if (fragment.getClass() == currentFragment.getClass()) {

                return true;
            }
        }
        return false;
    }

    private Fragment getCurrentFragment() {
        Fragment fragment;
        String[] avaibleFragments = getResources().getStringArray(R.array.fragments_tag_array);

        for (int i = 0; i < avaibleFragments.length; i++) {
            fragment = (Fragment) getFragmentManager().findFragmentByTag(avaibleFragments[i]);
            if (fragment != null) {
                if (fragment.isVisible()) return fragment;
            }

        }

        return null;
    }

    @NonNull
    private Fragment createFragmentOfIndex(int position) {

        String name = pageNames[position];

        if (name.equals("Main Page")) {

            return  new WelcomeFragment();
        }
        if (name.equals("Book List"))
        {
            return  BookFragment.newInstance(1);
        }
        if (name.equals("Account"))
        {
            return UserFragment.newInstance(currentUser);
        }
        return new WelcomeFragment();
    }


    private void setPageNames() {

        pageNames = new String[]{"Welcome Page", "Book List","Account"};

    }


    public void setWelcomeView() {



        Fragment fragment = new WelcomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, "WELCOME_FRAGMENT").addToBackStack(null).commit();

    }




    public void setSelectedBookView(Book book) {
        Fragment fragment = BookDetailsFragment.newInstance(book,currentUser);
        FragmentManager fragmentManager = getFragmentManager();

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment, "BOOK_DETAILS_FRAGMENT").addToBackStack(null).commit();

    }




    @Override
    public void userChanged(User user) {
        currentUser = user;
    }


    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            super.onBackPressed();
        }

        super.onBackPressed();


    }


}
