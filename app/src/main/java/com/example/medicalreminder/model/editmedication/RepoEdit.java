package com.example.medicalreminder.model.editmedication;

import android.content.Context;

import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestore;
import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestoreInterface;
import com.example.medicalreminder.local.LocalSource;
import com.example.medicalreminder.model.addmedication.Medication;

public class RepoEdit implements RepoEditInterface {

    Context context;
    LocalSource localSource;
    AddMedicationFirestoreInterface firestoreInterface;
    private static RepoEdit repository = null;

    private RepoEdit(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new AddMedicationFirestore();
    }

    public  static RepoEdit getInstance(Context context, LocalSource localSource){
        if(repository == null){
            repository = new RepoEdit(context,localSource);
        }
        return repository;
    }


    @Override
    public void getDrugDaysRealtime(Medication medication) {
        firestoreInterface.getDrugDaysRealtime(medication);
    }
}