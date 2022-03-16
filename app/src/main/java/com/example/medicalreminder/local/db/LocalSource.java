package com.example.medicalreminder.local.db;


import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;


public interface LocalSource {

        public void addDrug(MedicationList medDose );
        public LiveData<MedicationList> getDrugs(String date);
        public MedicationList getDrugsObj(String date);
        public void deleteDate(String date);
        public LiveData<List<MedicationList>> getAllDrugs();


    }

