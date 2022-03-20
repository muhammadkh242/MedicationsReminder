package com.example.medicalreminder.model.addmedication;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface RepoInterface {

    //room
    public void addDrug(MedicationList medDose );
    public void insertDrugDetails(Drug drug );
    public void deleteDate(String date );
    public LiveData<MedicationList> getDrugs(String date);
    public  LiveData<List<Drug>>getAllDrugDetails();

    //firestore
    public void sendDrug(MedicationList list);
    public MutableLiveData<List<MedicationList>> getDurgs(String date);


    //connection Network
    public boolean connection();



}
