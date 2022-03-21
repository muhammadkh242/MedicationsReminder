package com.example.medicalreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.medicalreminder.authentication.register.view.RegisterActivity;
import com.example.medicalreminder.local.sharedpref.SharedPref;
import com.example.medicalreminder.local.sharedpref.SharedPrefsInterface;
import com.example.medicalreminder.services.Notification;
import com.example.medicalreminder.services.Reply;
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
        //start listening for health tracker invitation

//        Intent intent = new Intent(Intent.ACTION_VIEW,
//                Uri.parse("mailto:muhammadkh233@gmail.com"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "TEST");
//        intent.putExtra(Intent.EXTRA_TEXT, "test test");
//        startActivity(intent);

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
//                .child("mrlHTT3zrgXXiTHPUtvoZr4bt6x2");
//        Query query = reference.orderByChild("name").equalTo("refo");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Drug drug = dataSnapshot.getValue(Drug.class);
//                    Drug drug1 = drug;
//                    drug1.setTotalPills(8);
//                    Log.i(TAG, "refo: " + drug.getTotalPills());
//                    Log.i(TAG, "refo key: " + dataSnapshot.getKey());
//                    reference.child(dataSnapshot.getKey())
//                            .setValue(drug1);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Dpublic Drug getDataRealTime(String name) {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("meds")
//                    .child("mrlHTT3zrgXXiTHPUtvoZr4bt6x2");
//            Query query = reference.orderByChild("name").equalTo("refo");
//            query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    Drug d  = task.getResult().getValue(Drug.class);
//                    Log.i(TAG, "DRUG: " +d.getName());
//                }
//            });
//            return drug;
//        }



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