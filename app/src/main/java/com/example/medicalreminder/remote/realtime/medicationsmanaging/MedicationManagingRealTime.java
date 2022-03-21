package com.example.medicalreminder.remote.realtime.medicationsmanaging;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicationManagingRealTime implements MedicationManagingRealTimeInterface {

    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    // Get All User Drugs
    @Override
    public MutableLiveData<List<Drug>> getNamesDrugsRealTime() {
        MutableLiveData<List<Drug>> medList = new MutableLiveData<>();
        List<Drug> list = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        Query query = db.child(userId).orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Drug drug = dataSnapshot.getValue(Drug.class);
                    list.add(drug);
                }
                medList.setValue(list);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return medList;
    }
}
