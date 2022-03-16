package com.example.medicalreminder.local.db;


import com.example.medicalreminder.model.addmedication.MedicationList;



public interface LocalSource {

        public void addDrug(MedicationList medDose );
        public MedicationList getDrugs(String date);
        public void deleteDate(String date);
    }

