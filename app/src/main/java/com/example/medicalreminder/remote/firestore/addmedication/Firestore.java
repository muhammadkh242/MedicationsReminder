package com.example.medicalreminder.remote.firestore.addmedication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.remote.realtime.RealTime;
import com.example.medicalreminder.remote.realtime.RealTimeInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Firestore implements FirestoreInterface {

    CollectionReference firebaseFirestore = FirebaseFirestore.getInstance().collection("Drug");
    List<MedicationList> list = new ArrayList<>();
    MutableLiveData<List<MedicationList>> medication = new MutableLiveData<>();
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    RealTimeInterface realTimeDBInterface = new RealTime();

    @Override
    public void insertDrugsOnline(MedicationList med) {
        firebaseFirestore
                .document(userId)
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
    public MutableLiveData<List<MedicationList>> getDrugsOnline(String date) {
        firebaseFirestore.document(userId)
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
    public void deleteDrugOnline(List<String> days, Medication medication) {
        for (int i = 0; i < days.size(); i++) {
            firebaseFirestore.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection(days.get(i))
                    .document(medication.getName())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    });
        }

        for (int i = 0; i < medication.getDays().size(); i++) {
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
            insertDrugsOnline(medicationList);
        }
        realTimeDBInterface.deleteDrugRealtime(medication.getName());
    }
}