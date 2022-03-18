package com.example.medicalreminder.firebase.addmedication;


import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.Map;

public interface FirestoreInterface {

    public void addDrugs(MedicationList list);
    public void getDrugs(String date);
}
