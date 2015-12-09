package com.mahageetamusic.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mahageetamusic.classes.LoginScreen;

import java.util.HashMap;

/**
 * Created by Kishore on 9/13/2015.
 */
public class UserSessionManager
{
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "mahamusicPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_USERID = "userid";
    public static final String KEY_MOBILENUMBER = "mobile_number";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CITY = "city";
    public static final String KEY_ZIPCODE = "zipcode";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_FADDRESS = "completeaddress";
    public static final String KEY_SHIPPING_COST = "shipping_charges";
    // Constructor
    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * @param zipcode
     * @param faddress
     * @param city
     * */
    public void createLoginSession(String userid, String firstname,
                                   String lastname, String email, String address, String mobilenumber, String city, String faddress, String zipcode) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_FIRSTNAME, firstname);
        editor.putString(KEY_LASTNAME, lastname);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_CITY, city);
        editor.putString(KEY_FADDRESS, faddress);
        editor.putString(KEY_ZIPCODE, zipcode);
        //Storing address in pref
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_MOBILENUMBER, mobilenumber);
        editor.putString(KEY_USERID, userid);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginScreen.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_FIRSTNAME, pref.getString(KEY_FIRSTNAME, ""));
        user.put(KEY_LASTNAME, pref.getString(KEY_LASTNAME, ""));
        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, ""));
        user.put(KEY_USERID, pref.getString(KEY_USERID, ""));
        user.put(KEY_MOBILENUMBER, pref.getString(KEY_MOBILENUMBER, ""));
        // user address
        user.put(KEY_ADDRESS, pref.getString(KEY_ADDRESS, ""));
        user.put(KEY_CITY, pref.getString(KEY_CITY, ""));
        user.put(KEY_FADDRESS, pref.getString(KEY_FADDRESS, ""));
        user.put(KEY_ZIPCODE, pref.getString(KEY_ZIPCODE, ""));
        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginScreen.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void saveShippingCost(String jsonArray){
        editor.putString(KEY_SHIPPING_COST,jsonArray);
        editor.commit();
    }

    public Object getShippingCharges(int range){
        return null;
    }
}
