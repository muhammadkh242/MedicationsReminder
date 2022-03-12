package com.example.medicalreminder.model;


import com.example.medicalreminder.firebase.auth.FirebaseDelegate;

public interface RepositoryInterface {


    void perForLogin(User user, FirebaseDelegate delegate);
    void perForAuth(User user, FirebaseDelegate delegate);
    void signOut();
}
