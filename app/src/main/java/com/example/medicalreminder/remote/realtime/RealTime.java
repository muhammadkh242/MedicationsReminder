package com.example.medicalreminder.remote.realtime;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.addmedication.Drug;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RealTime implements RealTimeInterface {

    List<String> days = new ArrayList<>();
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Drug drug = new Drug();

    //EditDrugs
    @Override
    public List<String> getDrugsDaysRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(userId);
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
        reference.child(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            Drug drug = snapshot.getValue(Drug.class);
                            if (drug.getName().equals(name)) {
                                reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                        child(snapshot.getKey()).removeValue();
                            }
                        }
                    }
                });
    }

    // Handle RefillReminder
    @Override
    public Drug getDataRealTime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(userId);
        Query query = reference.orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    drug = dataSnapshot.getValue(Drug.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return drug;
    }

    @Override
    public void updateRealTime(Drug drug) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(userId);
        Query query = reference.orderByChild("name").equalTo(drug.getName());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Drug drug1 = dataSnapshot.getValue(Drug.class);
                    drug1 = drug;
                    reference.child(dataSnapshot.getKey())
                            .setValue(drug1);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    // Get All User Drugs
    @Override
    public MutableLiveData<List<Drug>> getMedNamesRealTime() {
        MutableLiveData<List<Drug>> medList = new MutableLiveData<>();
        List<Drug> list = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        Query query = db.child(FirebaseAuth.getInstance().getUid()).orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Drug drug = dataSnapshot.getValue(Drug.class);
                    list.add(drug);
                    Log.i("TAG", "onDataChange: list size" + list.size());
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
