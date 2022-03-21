package com.example.medicalreminder.model.addmedication.reposatiry;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestore;
import com.example.medicalreminder.remote.firestore.addmedication.AddMedicationFirestoreInterface;
import com.example.medicalreminder.local.LocalSource;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTime;
import com.example.medicalreminder.remote.realtime.addmedication.AddMedicationRealTimeInterface;

import java.util.List;

public class RepoAdd implements RepoAddInterface {

    Context context;
    LocalSource localSource;
    AddMedicationFirestoreInterface firestoreInterface;
    AddMedicationRealTimeInterface realTimeInterface;
    private static RepoAdd repository;
    NetworkInfo activeNetwork;
    ConnectivityManager connectivityManager;

    private RepoAdd(Context context, LocalSource localSource){
        this.context = context;
       this.localSource = localSource;
       firestoreInterface = new AddMedicationFirestore();
       realTimeInterface = new AddMedicationRealTime();
    }

    public  static RepoAdd getInstance(Context context, LocalSource localSource){
        if(repository == null){
            repository = new RepoAdd(context,localSource);
        }
        return repository;
    }

    // ---------------------------------------ROOM
    //medication
    @Override
    public void insertMedicatinOffline(MedicationList medDose) {
     localSource.insertMedicationOffline(medDose);
    }
    @Override
    public LiveData<MedicationList> getDrugsOffline(String date) {
        return localSource.getMedsOffline(date);
    }
    @Override
    public void deleteDateOffline(String date) {
        localSource.deleteDateOffline(date);
    }

    //Drug
    @Override
    public void insertDrugOffline(Drug drug) {
        localSource.insertDrugOffline(drug);
    }
    @Override
    public LiveData<List<Drug>> getAllDrugDetailsOffline() {
        return localSource.getAllDrugDetailsOffline();
    }



    //----------------------------------FIRESTORE
    @Override
    public void insertMedicationFirestore(MedicationList list) {
        firestoreInterface.insertDrugsOnline(list);
    }
    @Override
    public MutableLiveData<List<MedicationList>> getDrugsOnline(String date) {
        return firestoreInterface.getDrugsOnline(date);
    }

    //------------------------realtime
    public void insertDrugRealTime(Drug drug){
   realTimeInterface.insertDrugRealTime(drug);
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
