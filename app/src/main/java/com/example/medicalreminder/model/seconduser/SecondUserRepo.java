package com.example.medicalreminder.model.seconduser;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.medicalreminder.remote.firestore.seconduser.SecondUserFirebaseClient;
import com.example.medicalreminder.remote.firestore.seconduser.SecondUserFirebaseInterface;
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
    public MutableLiveData<List<MedicationList>> getMeds(String date) {
        if(firebaseInterface.getMeds(date) == null){
            Log.i("TAG", "getMeds: no tracked users");
            Toast.makeText(context.getApplicationContext(), "No tracked users", Toast.LENGTH_SHORT).show();
        }
        return firebaseInterface.getMeds(date);
    }

    @Override
    public void deleteFriend() {
        firebaseInterface.deleteFriend();
    }
}
