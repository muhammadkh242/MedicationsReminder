package com.example.local;

import com.example.medicalreminder.model.User;

public interface SharedPrefsInterface {

    public void savePrefs(User user);
    public User getFromPrefs();
}
