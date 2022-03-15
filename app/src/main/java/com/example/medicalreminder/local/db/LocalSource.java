package com.example.medicalreminder.local.db;


import androidx.lifecycle.LiveData;

import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface LocalSource {

        public void addDrug(MedicationList medDose );
        public void deleteDrug(MedicationList medDose );
        public Single<MedicationList > getDrugs(MedicationList medicationList);
        public void deleteDate(String date);
    }

