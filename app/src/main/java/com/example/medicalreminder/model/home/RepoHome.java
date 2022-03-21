package com.example.medicalreminder.model.home;

import android.content.Context;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.medicalreminder.remote.firestore.addmedication.Firestore;
import com.example.medicalreminder.remote.firestore.addmedication.FirestoreInterface;
import com.example.medicalreminder.local.dbmedication.LocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.remote.realtime.RealTime;
import com.example.medicalreminder.remote.realtime.RealTimeInterface;
import com.example.medicalreminder.services.worker.RefillWorker;

public class RepoHome implements RepoHomeInterface{

    Context context;
    LocalSource localSource;
    private static RepoHome repo;
    FirestoreInterface firestoreInterface;
    RealTimeInterface realTimeDBInterface;

    private RepoHome(Context context, LocalSource localSource){
        this.context = context;
        this.localSource = localSource;
        firestoreInterface = new Firestore();
        realTimeDBInterface = new RealTime();
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
        realTimeDBInterface.updateRealTime(drug);
    }

    @Override
    public Drug getDrugRealTime(String name) {
        return realTimeDBInterface.getDataRealTime(name);
    }
}
