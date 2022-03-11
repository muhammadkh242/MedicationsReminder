package com.example.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.medicalreminder.model.User;

public class SharedPref implements SharedPrefsInterface {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User user;

    public SharedPref(Context context) {
        prefs = context.getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        editor = prefs.edit();
        user = new User();
    }

    @Override
    public void savePrefs(User user) {
        editor.putString("EMAIL", user.getEmail());
        editor.putString("PASSWORD", user.getPassword());
        editor.commit();
    }

    @Override
    public User getFromPrefs() {
        user.setEmail(prefs.getString("EMAIL", null));
        user.setPassword(prefs.getString("PASSWORD", null));
        return user;
    }
}
