package com.example.medicalreminder.firebase.auth;

public interface FirebaseDelegate {

    public void onFailureResult(String error);
    public void onSuccessResult(String success);
}
