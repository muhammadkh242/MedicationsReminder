package com.example.medicalreminder.firebase.seconduser;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SecondUserFirebaseClient implements SecondUserFirebaseInterface{

    FirebaseFirestore firestore  = FirebaseFirestore.getInstance();
    CollectionReference collectionReference  = firestore.collection("Drug");


    @Override
    public MutableLiveData<List<MedicationList>> getMeds() {

        MutableLiveData<List<MedicationList>> medList = new MutableLiveData<>();

        collectionReference.document("mrlHTT3zrgXXiTHPUtvoZr4bt6x2");

        return medList;
    }







    @Override
    public void storeMed(UserMed userMed) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        db.child(FirebaseAuth.getInstance().getUid()).push().setValue(userMed);

    }
}
