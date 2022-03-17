package com.example.medicalreminder.firebase.seconduser;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SecondUserFirebaseClient implements SecondUserFirebaseInterface{
    @Override
    public List<Med> getMeds() {
        return null;
    }

    @Override
    public void storeMed(UserMed userMed) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
        db.child(FirebaseAuth.getInstance().getUid()).push().setValue(userMed);

    }
}
