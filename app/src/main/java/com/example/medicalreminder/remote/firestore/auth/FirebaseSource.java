package com.example.medicalreminder.remote.firestore.auth;

import com.example.medicalreminder.model.authentication.User;

public interface FirebaseSource {

    public void perForAuth(User user, FirebaseDelegate delegate);
    public void perForLogin(User user, FirebaseDelegate delegate);
    public void logout();
}
