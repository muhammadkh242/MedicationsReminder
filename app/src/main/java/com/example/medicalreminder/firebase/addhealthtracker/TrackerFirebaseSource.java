package com.example.medicalreminder.firebase.addhealthtracker;

public interface TrackerFirebaseSource {
    public void sendInvitation(String email);

    public void storeUser(String email);
}
