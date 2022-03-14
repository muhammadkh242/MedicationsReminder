package com.example.medicalreminder.local.sharedpref;

import com.example.medicalreminder.model.authentication.User;

public interface SharedPrefsInterface {

    public User getFromPrefs();
    public void savePrefs(User user);
}
