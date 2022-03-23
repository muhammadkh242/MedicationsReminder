package com.example.medicalreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.services.service.MyNotification;
import com.example.medicalreminder.services.service.Reply;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private SharedPrefsInterface prefsInterface;

    List<String> days = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        prefsInterface = new SharedPref(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefsInterface.getFromPrefs().getEmail() != null) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    Intent intent = new Intent(getApplicationContext(), MyNotification.class);
                    startService(intent);
                    Intent intent1 = new Intent(getApplicationContext(), Reply.class);
                    startService(intent1);

                } else {
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                }
                finish();
            }
        }, 3000);

        Log.i(TAG, "onCreate: " + FirebaseAuth.getInstance().getUid());


        Log.i(TAG, "onCreate: " + getDrugDaysRealtime("panadol").size());
    }

    public List<String> getDrugDaysRealtime(String name) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
                .child("L2AbdT0LocWgGrRPatCKTzxYbz33");
        Query query = reference
                .orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                days = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Drug drug = dataSnapshot.getValue(Drug.class);
                    days = drug.getDays();
                    Log.i("TAG", "onDataChange: "+days.size());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return days;
    }

}