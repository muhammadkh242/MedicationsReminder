package com.example.medicalreminder.model.home;

import android.content.Context;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestore;
import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestoreInterface;
import com.example.medicalreminder.local.LocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTime;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTimeInterface;
import com.example.medicalreminder.remote.realtime.refillreminder.RefillReminderInterfaceRealTime;
import com.example.medicalreminder.remote.realtime.refillreminder.RefillReminderRealTime;
import com.example.medicalreminder.services.worker.RefillWorker;

public class RepoHome implements RepoHomeInterface{

    Context context;
    LocalSource localSource;
    private static RepoHome repo;
    AddMedicationFirestoreInterface firestoreInterface;
    RefillReminderInterfaceRealTime realTimeDBInterface;

    private RepoHome(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new AddMedicationFirestore();
        realTimeDBInterface = new RefillReminderRealTime();
    }

    public  static RepoHome getInstance(Context context, LocalSource localSource){
        if(repo == null){
            repo = new RepoHome(context,localSource);
        }
        return repo;
    }


    @Override
    public void updateDrugRealTime(Drug drug) {
        String name = drug.getName();
        Data data = new Data.Builder().putString("name", name).build();

        if(drug.getTotalPills() == drug.getRefill()){
            OneTimeWorkRequest request =  new OneTimeWorkRequest.Builder(RefillWorker.class)
                    .setInputData(data)
                    .build();
            WorkManager.getInstance(context).enqueue(request);
        }
        realTimeDBInterface.updateDrugRealTime(drug);
    }

    @Override
    public Drug getDrugRealTime(String name) {
        return realTimeDBInterface.getDrugRealtime(name);
    }
}
