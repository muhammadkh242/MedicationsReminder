package com.example.medicalreminder.firebase.auth;

import com.example.medicalreminder.model.User;

public interface FirebaseSource {

    public void perForAuth(User user, FirebaseDelegate delegate);
    public void perForLogin(User user, FirebaseDelegate delegate);
    public void logout();
}
