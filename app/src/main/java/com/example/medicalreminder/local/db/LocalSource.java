package com.example.medicalreminder.local.db;


import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public interface LocalSource {

        public void addDrug(MedicationList medDose );
        public void deleteDrug(MedicationList medDose );
        public LiveData<List<MedicationList >> getDrugs(String date);
    }

