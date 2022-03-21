package com.example.medicalreminder.model.addmedication.reposatiry;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.remote.firebase.addmedication.Firestore;
import com.example.medicalreminder.remote.firebase.addmedication.FirestoreInterface;
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

    // ---------------------------------------ROOM
    //medication
    @Override
    public void insertDrugOffline(MedicationList medDose) {
     localSource.insertDrugsOffline(medDose);
    }
    @Override
    public LiveData<MedicationList> getDrugsOffline(String date) {
        return localSource.getDrugsOffline(date);
    }
    @Override
    public void deleteDateOffline(String date) {
        localSource.deleteDateOffline(date);
    }

    //Drug
    @Override
    public void insertDrugDetailsOffline(Drug drug) {
        localSource.insertDrugDetails(drug);
    }
    @Override
    public LiveData<List<Drug>> getAllDrugDetailsOffline() {
        return localSource.getAllDrugDetails();
    }



    //----------------------------------FIRESTORE
    @Override
    public void insertDrugOnline( MedicationList list) {
        firestoreInterface.insertDrugsOnline(list);
    }
    @Override
    public MutableLiveData<List<MedicationList>> getDrugsOnline(String date) {
        return firestoreInterface.getDrugsOnline(date);
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
