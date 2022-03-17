package com.example.medicalreminder.firebase.seconduser;

import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;

import java.util.List;

public interface SecondUserFirebaseInterface {
    List<Med> getMeds();

    public void storeMed(UserMed userMed);
}
