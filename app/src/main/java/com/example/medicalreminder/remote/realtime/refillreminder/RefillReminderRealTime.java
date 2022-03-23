package com.example.medicalreminder.remote.realtime.refillreminder;

import android.util.Log;

import androidx.annotation.NonNull;

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

public class RefillReminderRealTime implements RefillReminderInterfaceRealTime{

//    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Drug drug = new Drug();

//    @Override
//    public Drug getDrugRealtime(String name) {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
//                .child(userId);
//        Query query = reference.orderByChild("name").equalTo(name);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    drug = dataSnapshot.getValue(Drug.class);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Log.i("TAG", "onDataChange:get " + drug.getTotalPills());
//        return drug;
//    }


    @Override
    public Drug getDrugRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query query = reference.orderByChild("name").equalTo(name);
        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                    Drug drug = snapshot.getValue(Drug.class);
                    Log.i("TAG", "onComplete: " + drug.getTotalPills());
                    drug.setTotalPills(drug.getTotalPills()-1);
                    updateDrugRealTime(drug);
                }
            }
        });
        return null;
    }

    @Override
    public void updateDrugRealTime(Drug drug) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Query query = reference.orderByChild("name").equalTo(drug.getName());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Drug drug1 = dataSnapshot.getValue(Drug.class);
                    drug1 = drug;
                    reference.child(dataSnapshot.getKey())
                            .setValue(drug1);
                    Log.i("TAG", "onDataChange:update " + drug1.getTotalPills());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
