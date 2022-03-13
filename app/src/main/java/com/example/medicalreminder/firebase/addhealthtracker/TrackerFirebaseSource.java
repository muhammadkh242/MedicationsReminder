package com.example.medicalreminder.firebase.addhealthtracker;

import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.firebase.database.DatabaseReference;

public interface TrackerFirebaseSource {
    public void sendInvitation(String email);
}
