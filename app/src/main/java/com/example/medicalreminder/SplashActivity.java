package com.example.medicalreminder;

import androidx.annotation.NonNull;
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
import com.example.medicalreminder.firebase.addmedication.Firestore;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.model.Invitation;
import com.example.medicalreminder.services.MyWorker;
import com.example.medicalreminder.services.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

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
        Log.i(TAG, "onCreate: " + FirebaseAuth.getInstance().getUid());
        //start listening for health tracker invitation

        Intent intent = new Intent(getApplicationContext(), Notification.class);
        startService(intent);


//    Invitation invitation = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail()
//    , "null",false);
//
//            DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
//            db.push().setValue(invitation);

//        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
//        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
//                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    Invitation invitation1 =  task.getResult().toObject(Invitation.class);
//                Log.i(TAG, "onComplete: " + invitation1.isInvitaion());
//                Log.i(TAG, "onComplete: " + invitation1.getEmail());
//            }
//        });



    }
}