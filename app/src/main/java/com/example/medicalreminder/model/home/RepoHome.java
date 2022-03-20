package com.example.medicalreminder.model.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.medicalreminder.firebase.addmedication.Firestore;
import com.example.medicalreminder.firebase.addmedication.FirestoreInterface;
import com.example.medicalreminder.local.dbmedication.LocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Repo;

public class RepoHome implements RepoHomeInterface{

    Context context;
    LocalSource localSource;
    private static RepoHome repo;
    FirestoreInterface firestoreInterface;

    private RepoHome(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new Firestore();
    }

    public  static RepoHome getInstance(Context context, LocalSource localSource){
        if(repo == null){
            repo = new RepoHome(context,localSource);
        }
        return repo;
    }


    @Override
    public void updateDrugRealTime(Drug drug) {
           firestoreInterface.updateRealTime(drug);
    }

    @Override
    public Drug getDrugRealTime(String name) {
        return firestoreInterface.getDataRealTime(name);
    }
}
