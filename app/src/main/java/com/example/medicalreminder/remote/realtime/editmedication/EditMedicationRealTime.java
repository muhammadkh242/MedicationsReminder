package com.example.medicalreminder.remote.realtime.editmedication;

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

import java.util.ArrayList;
import java.util.List;

public class EditMedicationRealTime implements EditMedicationInterfaceRealTime {

    List<String> days = new ArrayList<>();


    @Override
    public List<String> getDrugDaysRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
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
}
