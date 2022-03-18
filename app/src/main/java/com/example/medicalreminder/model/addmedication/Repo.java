package com.example.medicalreminder.model.addmedication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.example.medicalreminder.firebase.addmedication.Firestore;
import com.example.medicalreminder.firebase.addmedication.FirestoreInterface;
import com.example.medicalreminder.local.dbmedication.LocalSource;

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

    //medication
    @Override
    public void addDrug(MedicationList medDose  ) {
     localSource.addDrug(medDose);
    }
    @Override
    public LiveData<MedicationList> getDrugs(String date) {
        return localSource.getDrugs(date);
    }
    @Override
    public void deleteDate(String date) {
        localSource.deleteDate(date);
    }

    //Drug
    @Override
    public void insertDrugDetails(Drug drug) {
        localSource.insertDrugDetails(drug);
    }
    @Override
    public LiveData<List<Drug>> getAllDrugDetails() {
        return localSource.getAllDrugDetails();
    }



    //firestore
    @Override
    public void sendDrug( MedicationList list) {
        firestoreInterface.addDrugs(list);
    }
    @Override
    public void getDurgs(String date) {
        firestoreInterface.getDrugs(date);

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
