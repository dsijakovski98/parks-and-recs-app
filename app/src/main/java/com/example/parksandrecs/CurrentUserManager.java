package com.example.parksandrecs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import static android.content.Context.MODE_PRIVATE;

public class CurrentUserManager {
    public static String SHARED_PREFS_NAME = "userPrefs";
    public static String USER_KEY = "user_name";
    public static String LOGGED_IN_KEY = "logged_in";
    public static String USER_DEFAULT_VALUE = "User";

    public static void logIn(EditText input, Context context) {
        String username = input.getText().toString();
        boolean loggedIn = true; // ?

        // Create shared prefs object
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        // Create shared prefs editor
        SharedPreferences.Editor prefsEditor = sharedPrefs.edit();

        prefsEditor.putString(USER_KEY, username);
        prefsEditor.putBoolean(LOGGED_IN_KEY, loggedIn); // ?

        prefsEditor.apply();
    }

    public static void logOut(Context context) {
        // Create shared prefs object
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        // Create shared prefs editor
        SharedPreferences.Editor prefsEditor = sharedPrefs.edit();

        prefsEditor.putString(USER_KEY, "");
        prefsEditor.putBoolean(LOGGED_IN_KEY, false);

        prefsEditor.apply();

    }

    public static Bundle getCurrentUser(Context context) {
        Bundle userInfo = new Bundle();

        // Create shared prefs object
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        userInfo.putString(USER_KEY, sharedPrefs.getString(USER_KEY, ""));
        userInfo.putBoolean(LOGGED_IN_KEY, sharedPrefs.getBoolean(LOGGED_IN_KEY, false));

        return userInfo;
    }
}
