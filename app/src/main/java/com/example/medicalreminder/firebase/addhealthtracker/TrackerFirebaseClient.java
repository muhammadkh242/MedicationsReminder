package com.example.medicalreminder.firebase.addhealthtracker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TrackerFirebaseClient implements TrackerFirebaseSource{
    private static final String TAG = "TAG";
    private Context context;
    private static TrackerFirebaseClient trackerFirebaseClient = null;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("request_users");

    private TrackerFirebaseClient(Context context) {
        this.context = context;
    }

    public static TrackerFirebaseClient getTrackerFirebaseClient(Context context) {
        if(trackerFirebaseClient == null){
            trackerFirebaseClient = new TrackerFirebaseClient(context);
        }
        return trackerFirebaseClient;
    }

    @Override
    public void sendInvitation(String email) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");

        Query query = db.orderByChild("attached").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Log.i(TAG, "onDataChange: " + dataSnapshot.child("attached").getValue());
                    db.child(dataSnapshot.getKey()).child("attached").setValue(FirebaseAuth.getInstance().getUid());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query query = databaseReference.orderByChild("userEmail").equalTo(email);
//
//        Log.i(TAG, "sendInvitation: " + query.getRef().toString());
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    RequestUser user = dataSnapshot.getValue(RequestUser.class);
//                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("request_users");
//                    databaseReference.child(dataSnapshot.getKey()).child("request").setValue(true);
//                    databaseReference.child(dataSnapshot.getKey()).child("requesterID").setValue(FirebaseAuth.getInstance().getUid());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    @Override
    public void storeUser(String email) {

    }


}