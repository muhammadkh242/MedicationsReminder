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
    ConnectivityManager connectivityManager;

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

    //room
    @Override
    public void addDrug(MedicationList medDose ) {
     localSource.addDrug(medDose);
    }
    @Override
    public LiveData<MedicationList> getDrugs(String date) {
        return localSource.getDrugs(date);
    }
    @Override
    public LiveData<List<MedicationList>>  getAllDrugs() {
        return localSource.getAllDrugs();
    }
    @Override
    public void deleteDate(String date) {
        localSource.deleteDate(date);
    }

    @Override
    public void sendDrug( MedicationList list) {
        firestoreInterface.sendDrugs(list);
    }
    @Override
    public MedicationList getDurgs(MedicationList list) {
        firestoreInterface.getDrugs(list);
        return list;
    }

    public boolean connection(){
        boolean checkNetwork = false;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();
        if(activeNetwork!=null){
            checkNetwork = true;
        }
        return  checkNetwork;
    }



}
