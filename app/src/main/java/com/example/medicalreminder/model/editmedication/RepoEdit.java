package com.example.medicalreminder.model.editmedication;

import android.content.Context;

import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestore;
import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestoreInterface;
import com.example.medicalreminder.local.LocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTime;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTimeInterface;
import com.example.medicalreminder.remote.realtime.editmedication.EditMedicationInterfaceRealTime;
import com.example.medicalreminder.remote.realtime.editmedication.EditMedicationRealTime;

import java.util.List;

public class RepoEdit implements RepoEditInterface {

    Context context;
    LocalSource localSource;
    AddMedicationFirestoreInterface firestoreInterface;
    private static RepoEdit repository = null;
    EditMedicationInterfaceRealTime realTimeDBInterface;


    private RepoEdit(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new AddMedicationFirestore();
        realTimeDBInterface = new EditMedicationRealTime();
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
        return realTimeDBInterface.getDrugDaysRealtime(name);
    }

    @Override
    public void deleteDrugRealtime(String date) {
        realTimeDBInterface.deleteDrugRealtime(date);
    }

}
