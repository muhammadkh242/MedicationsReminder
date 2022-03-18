package com.example.medicalreminder.firebase.addmedication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firestore implements FirestoreInterface{

    CollectionReference firebaseFirestore = FirebaseFirestore.getInstance().collection("Drug");
    String userID = FirebaseAuth.getInstance().getUid();
    List<MedicationList> medication = new ArrayList<>();

    @Override
    public void addDrugs(MedicationList med) {
        firebaseFirestore
                .document(userID)
                .collection(med.getDate())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            firebaseFirestore.
                                    document(userID)
                                    .collection(med.getDate())
                                   .document(med.getList().get(0).getName())
                                    .set(med);
                            }
                        else{
                            firebaseFirestore
                                    .document(userID)
                                    .collection(med.getDate())
                                    .document(med.getList().get(0).getName())
                                    .set(med);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
     }

    @Override
    public List<MedicationList> getDrugs(String date) {
        firebaseFirestore.document("mrlHTT3zrgXXiTHPUtvoZr4bt6x2")
                .collection(date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            MedicationList medicationList = document.toObject(MedicationList.class);
                            medication.add(medicationList);
                        }
                    }
                });
        return medication;
    }

    }
