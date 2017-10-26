package com.inkubator.adryan.learnarabic.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.inkubator.adryan.learnarabic.activity.LoginActivity;
import com.inkubator.adryan.learnarabic.activity.MainActivity;

import java.util.HashMap;

/**
 * Created by adryanev on 16/10/17.
 */

public class SessionManager {

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LearnArabicPref";

    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ID_USER = "idUser";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TANGGAL_LAHIR = "tanggalLahir";



    //constuctor

    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String idUser, String username, String password, String nama, String email, String tanggalLahir){
        editor.putBoolean(IS_LOGGED_IN,true);
        editor.putString(KEY_ID_USER,idUser);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);
        editor.putString(KEY_NAMA,nama);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_TANGGAL_LAHIR,tanggalLahir);

        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(KEY_ID_USER,sharedPreferences.getString(KEY_ID_USER,null));
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME,null));
        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD,null));
        user.put(KEY_NAMA, sharedPreferences.getString(KEY_NAMA,null));
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL,null));
        user.put(KEY_TANGGAL_LAHIR, sharedPreferences.getString(KEY_TANGGAL_LAHIR,null));

        return user;
    }

    public void checkLogin(){
        if (!this.isLoggedIn()){

            Intent intent = new Intent(_context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(intent);
        }
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    private boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }
}
