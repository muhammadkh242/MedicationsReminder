package com.example.medicalreminder.firebase.addmedication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Firestore implements FirestoreInterface{

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference  = firebaseFirestore.collection("Drug");
    String userID = FirebaseAuth.getInstance().getUid();



    @Override
    public void sendDrugs( MedicationList list) {
        collectionReference.document("5NN9IC3lHiV2G0QoBq6hG3cyVtZ2")
        .set(list).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                getDrugs(list);
            }
        });
        }

    @Override
    public MedicationList getDrugs(MedicationList med) {

        firebaseFirestore.collection("Drug")
                .whereEqualTo("date",med.getDate())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                MedicationList c = d.toObject(MedicationList.class);
                                if(c.getDate().equals(med.getDate())){
//                                    for(int i =0;i<c.getList().size();i++){
//                                        med.getList().add(c.getList().get(i));
//                                    }
                                    Log.i("TAG", "onSuccess: "+med.getDate());
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("TAG", "onSuccess ssss ");
            }
        });

        Log.i("TAG", "getDrugs: "+med.getDate());
        return med;
    }

    public void delete(){
        firebaseFirestore.collection("Drug").document("5NN9IC3lHiV2G0QoBq6hG3cyVtZ2")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error deleting document", e);
                    }
                });
    }


}
