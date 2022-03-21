package com.example.medicalreminder.local.dbmedication;


import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;


public interface LocalSource {

        //medication
        public void insertMedicationOffline(MedicationList medList );
        public LiveData<MedicationList> getDrugsOffline(String date);
        public MedicationList getDrugsObjOffline(String date);
        public void deleteDateOffline(String date);


        //drug
       public void insertDrugOffline(Drug drug );
       public LiveData<Drug> getDrugDetails(String name);
       public LiveData<List<Drug>>getAllDrugDetails();
    }

