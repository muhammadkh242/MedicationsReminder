package com.example.medicalreminder.model.seconduser;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.firebase.seconduser.SecondUserFirebaseClient;
import com.example.medicalreminder.firebase.seconduser.SecondUserFirebaseInterface;
import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.addmedication.MedicationList;

import java.util.List;

public class SecondUserRepo implements SecondUserRepoInterface{

    SecondUserFirebaseInterface firebaseInterface;
    Context context;
    private static SecondUserRepo repo = null;


    public SecondUserRepo(Context context) {
        this.context = context;
        this.firebaseInterface = new SecondUserFirebaseClient();
    }

    public static SecondUserRepo getRepo(Context context){
        if(repo == null){
            repo = new SecondUserRepo(context);
        }
        return repo;
    }



    @Override
    public MutableLiveData<List<MedicationList>> getMeds() {
        return firebaseInterface.getMeds();
    }
}