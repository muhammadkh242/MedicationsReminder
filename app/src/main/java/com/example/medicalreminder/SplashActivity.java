package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.medicalreminder.addhealthtracker.view.InvitationService;
import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.services.MyWorker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private SharedPrefsInterface prefsInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Data data = new Data.Builder().putString("FIRST", "IN").build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(0, TimeUnit.MINUTES)
                .setInputData(data)
                .build();
        WorkManager.getInstance().enqueue(workRequest);

        prefsInterface = new SharedPref(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(prefsInterface.getFromPrefs().getEmail() != null){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                }
                finish();
            }
        }, 3000);
        Log.i(TAG, "onCreate: " + FirebaseAuth.getInstance().getUid());
        //start listening for health tracker invitation
        Intent intent = new Intent(getApplicationContext(), InvitationService.class);
        startService(intent);
//
        //DatabaseReference db = FirebaseDatabase.getInstance().getReference("request_users");
        //RequestUser user = new RequestUser(FirebaseAuth.getInstance().getUid(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), "NULL",false);
        //db.push().setValue(user);

        //test fire store
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference reference = db.collection("Drug").document("5MR2seN1CDl5XK5r6JfE");
//
//        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//            }
//        });
//
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
//        db.push().child(FirebaseAuth.getInstance().getUid()).child("attached").push().setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());

    }
}