package com.example.medicalreminder.remote.firestore.auth;

public interface FirebaseDelegate {

    public void onFailureResult(String error);
    public void onSuccessResult(String success);
}
