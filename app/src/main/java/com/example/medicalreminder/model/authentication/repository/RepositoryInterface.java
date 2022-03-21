package com.example.medicalreminder.model.authentication.repository;


import com.example.medicalreminder.model.authentication.User;
import com.example.medicalreminder.remote.firebase.auth.FirebaseDelegate;

public interface RepositoryInterface {


    void perForLogin(User user, FirebaseDelegate delegate);
    void perForAuth(User user, FirebaseDelegate delegate);
    void signOut();
}
