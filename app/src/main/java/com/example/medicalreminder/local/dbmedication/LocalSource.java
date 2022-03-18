package com.example.medicalreminder.local.dbmedication;


import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;


public interface LocalSource {

        //medication
        public void addDrug(MedicationList medDose );
        public LiveData<MedicationList> getDrugs(String date);
        public MedicationList getDrugsObj(String date);
        public void deleteDate(String date);


        //drug
       public void insertDrugDetails(Drug drug );
       public LiveData<Drug> getDrugDetails(String name);
       public LiveData<List<Drug>>getAllDrugDetails();
    }

