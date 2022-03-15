package com.example.medicalreminder.firebase.addmedication;


import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.Map;

public interface FirestoreInterface {

    public void sendDrugs(MedicationList list);
    public MedicationList getDrugs(MedicationList list);
}
