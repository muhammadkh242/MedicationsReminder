package com.example.medicalreminder.firebase.seconduser;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.Invitation;
import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SecondUserFirebaseClient implements SecondUserFirebaseInterface{


    CollectionReference fireStore = FirebaseFirestore.getInstance().collection("Drug");
    List<MedicationList> list = new ArrayList<>();
    MutableLiveData<List<MedicationList>> medList = new MutableLiveData<>();

    public MutableLiveData<List<MedicationList>> getData(String date, String id) {
        MutableLiveData<List<MedicationList>> dataList = new MutableLiveData<>();

        List<MedicationList> list = new ArrayList<>();
        fireStore.document(id)
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
                        Log.i("TAG", "onComplete:  LIST SIZE" + list.size());
                        dataList.setValue(list);

                    }
                });
        return dataList;
    }

    @Override
    public MutableLiveData<List<MedicationList>> getMeds(String date){
        FirebaseFirestore.getInstance().collection("Notifications")
                .document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Invitation invitation = task.getResult().toObject(Invitation.class);
                String id = invitation.getId();
                medList = getData(date, id);
                Log.i("TAG", "ID FOR THE SECOND ONE: " + id);
            }
        });
        return medList;
    }

    @Override
    public void storeMed(Drug drug) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        db.child(FirebaseAuth.getInstance().getUid()).push().setValue(drug);

    }


}
