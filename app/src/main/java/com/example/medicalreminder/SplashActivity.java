package com.example.medicalreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.services.service.MyNotification;
import com.example.medicalreminder.services.service.Reply;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private SharedPrefsInterface prefsInterface;

    List<String> days = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Log.i("TAG", "onCreate: ");
        try {
            Log.i("TAG", "onCreate: TRY");
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.medicalreminder",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("TAG:","----" +  Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("TAG", "onCreate: catch1" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.i("TAG", "onCreate: catch2");
        }
        Log.i("TAG", "onCreate: agter");

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



    }





}