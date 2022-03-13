package com.example.medicalreminder.model.healthtracker;

import android.content.Context;

import com.example.medicalreminder.firebase.addhealthtracker.TrackerFirebaseClient;
import com.example.medicalreminder.firebase.addhealthtracker.TrackerFirebaseSource;

public class TrackerRepo implements TrackerRepoInterface{
    TrackerFirebaseSource trackerFirebaseSource;
    private static TrackerRepo trackerRepo = null;
    private Context context;

    private TrackerRepo(TrackerFirebaseSource trackerFirebaseSource, Context context) {
        this.trackerFirebaseSource = trackerFirebaseSource;
        this.context = context;
    }

    public static TrackerRepo getInstance(TrackerFirebaseSource trackerFirebaseSource, Context context){
        if(trackerRepo == null){
            trackerRepo = new TrackerRepo(trackerFirebaseSource, context);
        }
        return trackerRepo;
    }


    @Override
    public void sendInvitation(String email) {
        trackerFirebaseSource.sendInvitation(email);
    }
}
