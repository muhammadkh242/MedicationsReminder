package com.example.medicalreminder.local;


import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;


public interface LocalSource {

    //medication
    public void insertMedicationOffline(MedicationList medList );
    public LiveData<MedicationList> getMedsOffline(String date);
    public MedicationList getMedsObjOffline(String date);
    public void deleteDateOffline(String date);


    //drug
    public void insertDrugOffline(Drug drug );
    public LiveData<Drug> getDrugDetailsOffline(String name);
    public LiveData<List<Drug>> getAllDrugDetailsOffline();
}