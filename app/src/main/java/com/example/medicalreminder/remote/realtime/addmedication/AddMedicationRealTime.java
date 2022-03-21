package com.example.medicalreminder.remote.realtime.addmedication;

import com.example.medicalreminder.model.addmedication.Drug;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddMedicationRealTime implements AddMedicationRealTimeInterface {

    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    public void insertDrugRealTime(Drug drug) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        db.child(userId).push().setValue(drug);

    }
    
}
