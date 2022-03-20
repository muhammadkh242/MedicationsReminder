package com.example.medicalreminder.model.editmedication;

import android.content.Context;

import com.example.medicalreminder.firebase.addmedication.Firestore;
import com.example.medicalreminder.firebase.addmedication.FirestoreInterface;
import com.example.medicalreminder.local.dbmedication.LocalSource;
import com.example.medicalreminder.model.addmedication.Medication;

import java.util.List;

public class RepoEdit implements RepoEditInterface {

    Context context;
    LocalSource localSource;
    FirestoreInterface firestoreInterface;
    private static RepoEdit repository;


    private RepoEdit(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new Firestore();
    }

    public  static RepoEdit getInstance(Context context, LocalSource localSource){
        if(repository == null){
            repository = new RepoEdit(context,localSource);
        }
        return repository;
    }


    @Override
    public void deleteDrugFirestore(List<String> days, Medication medication) {
        firestoreInterface.deleteDrugFireStore(days,medication);
    }

    //realtime
    @Override
    public List<String> getDrugsDaysRealtime(String name) {
        return firestoreInterface.getDrugsDaysRealtime(name);
    }

    @Override
    public void deleteDrugRealtime(String date) {
        firestoreInterface.deleteDrugRealtime(date);
    }

    //firestore

}
