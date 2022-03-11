package com.example.medicalreminder.firebase.auth;

import com.example.medicalreminder.model.User;

public interface FirebaseSource {

    public void perForAuth(User user);
    public void perForLogin(User user);
    public void logout();
}
