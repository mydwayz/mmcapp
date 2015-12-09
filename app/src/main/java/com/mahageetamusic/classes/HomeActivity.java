package com.mahageetamusic.classes;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.AlbumBean;
import com.mahageetamusic.beans.CategoryBean;
import com.mahageetamusic.beans.ResponseObject;
import com.mahageetamusic.beans.SongsAndRingtones;
import com.mahageetamusic.fragments.AboutUs;
import com.mahageetamusic.fragments.AlbumFragment;
import com.mahageetamusic.fragments.Categories;
import com.mahageetamusic.fragments.CategoryAlbums;
import com.mahageetamusic.fragments.ContactUs;
import com.mahageetamusic.fragments.Help;
import com.mahageetamusic.fragments.RequestServices;
import com.mahageetamusic.interfaces.OnFragmentInteractionListener;
import com.mahageetamusic.network.RequestAsyncTask;
import com.mahageetamusic.utils.Utils;

import java.util.List;

/**
 * Created by Kishore on 9/8/2015.
 */
public class HomeActivity extends MahageetaMusicBaseActivity implements View.OnClickListener,OnFragmentInteractionListener {
    @Override
    public void onResponseListener(int requestFor, Object mResponse) {
        Log.v("Response", "" + mResponse);
        //dismissProgressDialog();
        ResponseObject responseObject = null;
        if (requestFor == Utils.REQUEST_CATEOGIES) {
            dismissProgressDialog();
            responseObject = (ResponseObject) mResponse;
            if (responseObject.getmResponseStatus().equals("Success")) {
                if (responseObject.getmResponse() instanceof List<?>) {
                    if (fragmentManager == null) {
                        fragmentManager = getSupportFragmentManager();
                    }
                    Log.v("HomeActivity", "Creating Category Fragment");
                    fragmentTransaction = fragmentManager.beginTransaction();
                    categoriesFragment = Categories.newInstance("Category", (List<CategoryBean>) responseObject.getmResponse());
                    fragmentTransaction.replace(R.id.contentpanel, categoriesFragment, "Category").commit();
                    appbar_title.setText(navTitles[0]);
                }
            } else {
                showAlertDialog("Music Categories", true, responseObject.getmResponse().toString(), "OK", null, null, null);
            }
        } else if(requestFor==Utils.REQUEST_PER_CATEGORY){
            dismissProgressDialog();
            responseObject = (ResponseObject)mResponse;
            if(responseObject.getmResponseStatus().equals("Success")){
                if(responseObject.getmResponse() instanceof List<?>){
                    if(fragmentManager == null){
                        fragmentManager = getSupportFragmentManager();
                    }
                    Log.v("HomeActivity","Creating CategoryAlbums Fragment");
                    fragmentTransaction = fragmentManager.beginTransaction();
                    categoryAlbums = CategoryAlbums.newInstance("CategoryAlbums",(List<AlbumBean>)responseObject.getmResponse());
                    fragmentTransaction.replace(R.id.contentpanel,categoryAlbums,"Albums").commit();
                }
            }else{
                showAlertDialog("Category Albums",true,responseObject.getmResponse().toString(),"OK",null,null,null);
            }
        } else if(requestFor==Utils.REQUEST_ALBUM_SONGS){
            dismissProgressDialog();
            responseObject = (ResponseObject)mResponse;
            if(responseObject.getmResponseStatus().equals("Success")){
                Toast.makeText(HomeActivity.this,"Toast Success",Toast.LENGTH_SHORT).show();
                fragmentTransaction = fragmentManager.beginTransaction();
                albumFragment = AlbumFragment.newInstance("AlbumView",(SongsAndRingtones)responseObject.getmResponse());
                fragmentTransaction.replace(R.id.contentpanel,albumFragment,"AlbumView").commit();
            }else{
                showAlertDialog("Albums",true,responseObject.getmResponse().toString(),"OK",null,null,null);
            }
        }
        mDrawerLayout.openDrawer(mDrawerListView);
        RL_Categories.performClick();
    }

    private Toolbar toolbar;
    private String[] navTitles;
    private TextView appbar_title, appbar_cart;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawerListView;
    CharSequence mTitle, mDrawerTitle;
    RelativeLayout RL_Categories, RL_MyCart, RL_Services, RL_Help, RL_AboutUs, RL_ContactUs;
    ImageView IV_Categories, IV_MyCart, IV_Services, IV_Help, IV_AboutUs, IV_ContactUs;
    CheckBox CB_Categories, CB_MyCart, CB_Services, CB_Help, CB_AboutUs, CB_ContactUs;
    TextView header;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Categories categoriesFragment;
    RequestServices services;
    AboutUs aboutUsFragment;
    Help helpFragment;
    ContactUs contactUsFragment;
    CategoryAlbums categoryAlbums;
    AlbumFragment albumFragment;
    View appbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        navTitles = getResources().getStringArray(R.array.nav_drawer_items);
        RL_Categories = (RelativeLayout) findViewById(R.id.menu1);
        IV_Categories = (ImageView) findViewById(R.id.icon1);
        CB_Categories = (CheckBox) findViewById(R.id.item_checkbox1);

        RL_MyCart = (RelativeLayout) findViewById(R.id.menu2);
        IV_MyCart = (ImageView) findViewById(R.id.icon2);
        CB_MyCart = (CheckBox) findViewById(R.id.item_checkbox2);

        RL_Services = (RelativeLayout) findViewById(R.id.menu3);
        IV_Services = (ImageView) findViewById(R.id.icon3);
        CB_Services = (CheckBox) findViewById(R.id.item_checkbox3);

        RL_Help = (RelativeLayout) findViewById(R.id.menu4);
        IV_Help = (ImageView) findViewById(R.id.icon4);
        CB_Help = (CheckBox) findViewById(R.id.item_checkbox4);

        RL_AboutUs = (RelativeLayout) findViewById(R.id.menu5);
        IV_AboutUs = (ImageView) findViewById(R.id.icon5);
        CB_AboutUs = (CheckBox) findViewById(R.id.item_checkbox5);

        RL_ContactUs = (RelativeLayout) findViewById(R.id.menu6);
        IV_ContactUs = (ImageView) findViewById(R.id.icon6);
        CB_ContactUs = (CheckBox) findViewById(R.id.item_checkbox6);

        RL_Categories.setOnClickListener(this);
        RL_MyCart.setOnClickListener(this);
        RL_Services.setOnClickListener(this);
        RL_Help.setOnClickListener(this);
        RL_AboutUs.setOnClickListener(this);
        RL_ContactUs.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
        mTitle = mDrawerTitle = getTitle();

        mDrawerListView = (LinearLayout) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_arrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_menu);
        appbar_title = (TextView) toolbar.findViewById(R.id.appbar_title);
        appbar_cart = (TextView) toolbar.findViewById(R.id.add2cart);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                //appbar_title.setText(mDrawerTitle);
                toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
                //appbar_title.setText(mTitle);
                toolbar.setNavigationIcon(R.drawable.ic_navigation_menu);
                invalidateOptionsMenu();
                //RL_Services.performClick();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            new RequestAsyncTask(HomeActivity.this, this, Utils.REQUEST_CATEOGIES).execute();
            showProgressDialog(null, "Loading Categories");
        }

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.add2cart:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
//        menu.findItem(R.id.add2cart).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        mDrawerLayout.closeDrawer(mDrawerListView);
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 1:
                fragment = new Categories();
                break;
            case 2:
                services = RequestServices.newInstance(null, null);
                fragmentManager.beginTransaction()
                        .replace(R.id.contentpanel, services, navTitles[position]).addToBackStack(navTitles[position]).commit();
                break;
            case 3:
                helpFragment = Help.newInstance(null, null);
                fragmentManager.beginTransaction()
                        .replace(R.id.contentpanel, helpFragment, navTitles[position]).addToBackStack(navTitles[position]).commit();
                break;
            case 4:
                aboutUsFragment = AboutUs.newInstance(null, null);
                fragmentManager.beginTransaction()
                        .replace(R.id.contentpanel, aboutUsFragment, navTitles[position]).addToBackStack(navTitles[position]).commit();
                break;
            case 5:
                contactUsFragment = ContactUs.newInstance(null,null);
                fragmentManager.beginTransaction()
                        .replace(R.id.contentpanel, contactUsFragment, navTitles[position]).addToBackStack(navTitles[position]).commit();
                break;

            default:
                if(categoriesFragment==null){
                categoriesFragment = new Categories();}
                fragmentManager.beginTransaction()
                        .replace(R.id.contentpanel, categoriesFragment, navTitles[position]).commit();
                break;
        }

        /*if (fragment != null) {

            fragmentManager.beginTransaction()
                    .replace(R.id.contentpanel, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerLayout.closeDrawer(mDrawerListView);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }*/
    }

    private void setMenuIdentity(int highlight) {
        switch (highlight) {
            case 1:
                //My Cart
                IV_Categories.setSelected(false);
                CB_Categories.setChecked(false);
                IV_MyCart.setSelected(true);
                CB_MyCart.setChecked(true);
                IV_Services.setSelected(false);
                CB_Services.setChecked(false);
                IV_Help.setSelected(false);
                CB_Help.setChecked(false);
                IV_AboutUs.setSelected(false);
                CB_AboutUs.setChecked(false);
                IV_ContactUs.setSelected(false);
                CB_ContactUs.setChecked(false);
                break;
            case 2:
                //Request_Services
                IV_Categories.setSelected(false);
                CB_Categories.setChecked(false);
                IV_MyCart.setSelected(false);
                CB_MyCart.setChecked(false);
                IV_Services.setSelected(true);
                CB_Services.setChecked(true);
                IV_Help.setSelected(false);
                CB_Help.setChecked(false);
                IV_AboutUs.setSelected(false);
                CB_AboutUs.setChecked(false);
                IV_ContactUs.setSelected(false);
                CB_ContactUs.setChecked(false);
                break;
            case 3:
                //Help
                IV_Categories.setSelected(false);
                CB_Categories.setChecked(false);
                IV_MyCart.setSelected(false);
                CB_MyCart.setChecked(false);
                IV_Services.setSelected(false);
                CB_Services.setChecked(false);
                IV_Help.setSelected(true);
                CB_Help.setChecked(true);
                IV_AboutUs.setSelected(false);
                CB_AboutUs.setChecked(false);
                IV_ContactUs.setSelected(false);
                CB_ContactUs.setChecked(false);
                break;
            case 4:
                //About Us
                IV_Categories.setSelected(false);
                CB_Categories.setChecked(false);
                IV_MyCart.setSelected(false);
                CB_MyCart.setChecked(false);
                IV_Services.setSelected(false);
                CB_Services.setChecked(false);
                IV_Help.setSelected(false);
                CB_Help.setChecked(false);
                IV_AboutUs.setSelected(true);
                CB_AboutUs.setChecked(true);
                IV_ContactUs.setSelected(false);
                CB_ContactUs.setChecked(false);
                break;
            case 5:
                //Contact Us
                IV_Categories.setSelected(false);
                CB_Categories.setChecked(false);
                IV_MyCart.setSelected(false);
                CB_MyCart.setChecked(false);
                IV_Services.setSelected(false);
                CB_Services.setChecked(false);
                IV_Help.setSelected(false);
                CB_Help.setChecked(false);
                IV_AboutUs.setSelected(false);
                CB_AboutUs.setChecked(false);
                IV_ContactUs.setSelected(true);
                CB_ContactUs.setChecked(true);
                break;
            default:
                //Categories
                IV_Categories.setSelected(true);
                CB_Categories.setChecked(true);
                IV_MyCart.setSelected(false);
                CB_MyCart.setChecked(false);
                IV_Services.setSelected(false);
                CB_Services.setChecked(false);
                IV_Help.setSelected(false);
                CB_Help.setChecked(false);
                IV_AboutUs.setSelected(false);
                CB_AboutUs.setChecked(false);
                IV_ContactUs.setSelected(false);
                CB_ContactUs.setChecked(false);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu1:
                mTitle = navTitles[0];
                setMenuIdentity(0);
                appbar_title.setText(navTitles[0]);
                displayView(0);
                break;
            case R.id.menu2:
                mTitle = navTitles[1];
                setMenuIdentity(1);
                appbar_title.setText(navTitles[1]);
                //displayView(1);
                break;
            case R.id.menu3:
                mTitle = navTitles[2];
                setMenuIdentity(2);
                displayView(2);
                appbar_title.setText(navTitles[2]);
                break;
            case R.id.menu4:
                mTitle = navTitles[3];
                setMenuIdentity(3);
                displayView(3);
                appbar_title.setText(navTitles[3]);
                break;
            case R.id.menu5:
                mTitle = navTitles[4];
                setMenuIdentity(4);
                displayView(4);
                appbar_title.setText(navTitles[4]);
                break;
            case R.id.menu6:
                mTitle = navTitles[5];
                setMenuIdentity(5);
                displayView(5);
                appbar_title.setText(navTitles[5]);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(String name,Object... objects) {
        if(name.equals("Category")){
            new RequestAsyncTask(HomeActivity.this, this, Utils.REQUEST_PER_CATEGORY).execute(((CategoryBean)objects[0]).getCategoryID());
            showProgressDialog(null, "Loading Albums");
        } else if(name.equals("CategoryAlbum")){
            new RequestAsyncTask(HomeActivity.this, this, Utils.REQUEST_ALBUM_SONGS).execute(((AlbumBean) objects[0]).getAlbumID());
            //showProgressDialog(null, "Loading Albums");
        }
    }
}
