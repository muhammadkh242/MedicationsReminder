package com.example.medicalreminder.local;

import com.example.medicalreminder.model.User;

public interface SharedPrefsInterface {

    public User getFromPrefs();
    public void savePrefs(User user);
}
