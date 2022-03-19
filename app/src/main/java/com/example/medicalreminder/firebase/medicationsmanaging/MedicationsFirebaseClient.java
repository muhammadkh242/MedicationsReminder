package com.example.medicalreminder.firebase.medicationsmanaging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.model.UserMed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MedicationsFirebaseClient implements MedicationsFirebaseInterface{
//    List<UserMed> list = new ArrayList<>();

//    MutableLiveData<List<UserMed>> medList = new MutableLiveData<>();
    @Override
    public MutableLiveData<List<UserMed>> getMeds() {
        MutableLiveData<List<UserMed>> medList = new MutableLiveData<>();
        List<UserMed> list = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        Query query = db.child(FirebaseAuth.getInstance().getUid()).orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserMed userMed = dataSnapshot.getValue(UserMed.class);
                    Log.i("TAG", "form: " + userMed.getForm());
                    Log.i("TAG", "name: " + userMed.getName());
                    Log.i("TAG", "___________________________");
                    list.add(userMed);
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
