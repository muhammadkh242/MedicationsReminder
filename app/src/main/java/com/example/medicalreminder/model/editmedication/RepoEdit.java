package com.example.medicalreminder.model.editmedication;

import android.content.Context;

import com.example.medicalreminder.remote.firebase.addmedication.Firestore;
import com.example.medicalreminder.remote.firebase.addmedication.FirestoreInterface;
import com.example.medicalreminder.local.dbmedication.LocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.remote.realtimedb.RealTimeDB;
import com.example.medicalreminder.remote.realtimedb.RealTimeDBInterface;

import java.util.List;

public class RepoEdit implements RepoEditInterface {

    Context context;
    LocalSource localSource;
    FirestoreInterface firestoreInterface;
    private static RepoEdit repository = null;
    RealTimeDBInterface realTimeDBInterface;


    private RepoEdit(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new Firestore();
        realTimeDBInterface = new RealTimeDB();
    }

    public  static RepoEdit getInstance(Context context, LocalSource localSource){
        if(repository == null){
            repository = new RepoEdit(context,localSource);
        }
        return repository;
    }


    @Override
    public void deleteDrugFirestore(List<String> days, Medication medication) {
        firestoreInterface.deleteDrugOnline(days,medication);
    }

    @Override
    public List<String> getDrugsDaysRealtime(String name) {
        return realTimeDBInterface.getDrugsDaysRealtime(name);
    }

    @Override
    public void deleteDrugRealtime(String date) {
        realTimeDBInterface.deleteDrugRealtime(date);
    }

}
