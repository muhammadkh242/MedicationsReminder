package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.medicalreminder.addhealthtracker.view.InvitationService;
import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.firebase.auth.FirebaseClient;
import com.example.medicalreminder.local.SharedPref;
import com.example.medicalreminder.local.SharedPrefsInterface;
import com.example.medicalreminder.model.Repository;
import com.example.medicalreminder.model.RepositoryInterface;
//import com.example.medicalreminder.firebase.healthtracker.HealthTrackersClient;
//import com.example.medicalreminder.model.healthtracker.HealthTrackerUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private SharedPrefsInterface prefsInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
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

        //star listening for health tracker invitation
        Intent intent = new Intent(getApplicationContext(), InvitationService.class);
        startService(intent);
//        HealthTrackerUser healthTrackerUser = new HealthTrackerUser("30", null, false, "moا@gmail");
//        DatabaseReference databaseReference = HealthTrackersClient.getDatabaseReference();
//        databaseReference.push().setValue(healthTrackerUser);




    }
}