package com.example.medicalreminder.model.addmedication.reposatiry;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface RepoAddInterface {

    //room
    void insertMedicatinOffline(MedicationList medList);
    void insertDrugOffline(Drug drug);
    void deleteDateOffline(String date);
    LiveData<MedicationList> getDrugsOffline(String date);
    LiveData<List<Drug>> getAllDrugDetailsOffline();


    //firestore
    void insertMedicationFirestore(MedicationList list);
    MutableLiveData<List<MedicationList>> getDrugsOnline(String date);

    //realtime
    void insertDrugRealTime(Drug drug);

    //connection Network
    boolean connection();

}
