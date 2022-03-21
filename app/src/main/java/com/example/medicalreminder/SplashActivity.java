package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.services.service.Notification;
import com.example.medicalreminder.services.service.Reply;
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
                    Intent intent1 = new Intent(getApplicationContext(), Reply.class);
                    startService(intent1);

                } else {
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                }
                finish();
            }
        }, 3000);

        Log.i(TAG, "onCreate: " + FirebaseAuth.getInstance().getUid());
    }
}