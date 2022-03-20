package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.services.Notification;
import com.google.firebase.auth.FirebaseAuth;

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
                if (prefsInterface.getFromPrefs().getEmail() != null) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    Intent intent = new Intent(getApplicationContext(), Notification.class);
                    startService(intent);
                } else {
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                }
                finish();
            }
        }, 3000);

        Log.i(TAG, "onCreate: " + FirebaseAuth.getInstance().getUid());
        //start listening for health tracker invitation




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