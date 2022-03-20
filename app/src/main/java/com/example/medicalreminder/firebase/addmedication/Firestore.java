package com.example.medicalreminder.firebase.addmedication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Firestore implements FirestoreInterface {

    CollectionReference firebaseFirestore = FirebaseFirestore.getInstance().collection("Drug");
    String userID = FirebaseAuth.getInstance().getUid();
    List<MedicationList> list = new ArrayList<>();
    List<String> days = new ArrayList<>();
    MutableLiveData<List<MedicationList>> medication = new MutableLiveData<>();
    String userId = FirebaseAuth.getInstance().getUid();

    @Override
    public void addDrugs(MedicationList med) {
        firebaseFirestore
                .document("mrlHTT3zrgXXiTHPUtvoZr4bt6x2")
                .collection(med.getDate())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            firebaseFirestore.
                                    document(userId)
                                    .collection(med.getDate())
                                    .document(med.getList().get(0).getName())
                                    .set(med);
                        } else {
                            firebaseFirestore
                                    .document(userId)
                                    .collection(med.getDate())
                                    .document(med.getList().get(0).getName())
                                    .set(med);
                            Log.i("TAG", "size: " + med.getList().size());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    @Override
    public MutableLiveData<List<MedicationList>> getDrugs(String date) {
        firebaseFirestore.document("mrlHTT3zrgXXiTHPUtvoZr4bt6x2")
                .collection(date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            MedicationList medicationList = document.toObject(MedicationList.class);
                            Log.i("TAG", "onComplete: " + medicationList.getList().size());
                            list.add(medicationList);
                        }
                        medication.setValue(list);
                        list = new ArrayList<>();
                    }
                });
        return medication;
    }

    @Override
    public void deleteDrugFireStore(List<String> days, Medication medication) {
        for (int i = 0; i < days.size(); i++) {
            firebaseFirestore.document("mrlHTT3zrgXXiTHPUtvoZr4bt6x2")
                    .collection(days.get(i))
                    .document(medication.getName())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    });
        }

        for(int i =0 ; i<medication.getDays().size(); i++){
            MedicationList medicationList = new MedicationList();
            List<MedicationDose> medicationDoses = new ArrayList<>();
            for (int j = 0; j < medication.getHours().size(); j++) {
                MedicationDose med = new MedicationDose();
                med.setName(medication.getName());
                med.setHour(medication.getHours().get(j));
                medicationDoses.add(med);
            }
            medicationList.setDate(medication.getDays().get(i));
            medicationList.setList(medicationDoses);
            addDrugs(medicationList);
        }
        deleteDrugRealtime(medication.getName());
    }

    //realtime
    @Override
    public List<String> getDrugsDaysRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(FirebaseAuth.getInstance().getUid());
        Query query = reference
                .orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                days = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Drug drug = dataSnapshot.getValue(Drug.class);
                    days = drug.getDays();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return days;
    }
    public void deleteDrugRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds");
        reference.child("mrlHTT3zrgXXiTHPUtvoZr4bt6x2").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            Drug drug = snapshot.getValue(Drug.class);
                            if (drug.getName().equals(name)) {
                                reference.child("mrlHTT3zrgXXiTHPUtvoZr4bt6x2").
                                        child(snapshot.getKey()).removeValue();
                            }
                        }
                    }
                });
    }
}