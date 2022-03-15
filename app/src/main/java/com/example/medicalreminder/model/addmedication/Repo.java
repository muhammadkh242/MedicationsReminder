package com.example.medicalreminder.model.addmedication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.firebase.addmedication.Firestore;
import com.example.medicalreminder.firebase.addmedication.FirestoreInterface;
import com.example.medicalreminder.local.db.LocalSource;
import java.util.List;

public class Repo implements RepoInterface {

    Context context;
    LocalSource localSource;
    FirestoreInterface firestoreInterface;
    private static Repo repository;
    NetworkInfo activeNetwork;
    ConnectivityManager cm;

    private Repo(Context context, LocalSource localSource){
        this.context = context;
       this.localSource = localSource;
       firestoreInterface = new Firestore();
    }

    public  static Repo getInstance(Context context, LocalSource localSource){
        if(repository == null){
            repository = new Repo(context,localSource);
        }
        return repository;
    }


    @Override
    public void addDrug(MedicationList medDose  ) {
        localSource.addDrug(medDose);
    }

    @Override
    public void deleteDrug(MedicationList medDose ) {
        localSource.deleteDrug(medDose);
    }

    @Override
    public LiveData<List<MedicationList >> getDrugs(String date) {
        return localSource.getDrugs(date);
    }

    @Override
    public void sendDrug( MedicationList list) {
        firestoreInterface.sendDrugs(list);
    }

    public boolean connection(){
        boolean checkNetwork = false;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork!=null){
            checkNetwork = true;
        }
        return  checkNetwork;
    }

    @Override
    public MedicationList getDurgs(MedicationList list) {
        firestoreInterface.getDrugs(list);
        return null;
    }

    @Override
    public void createDocument() {
    }

}
