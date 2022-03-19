package com.example.medicalreminder.firebase.addhealthtracker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.Invitation;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TrackerFirebaseClient implements TrackerFirebaseSource{
    private static final String TAG = "TAG";
    private Context context;
    private static TrackerFirebaseClient trackerFirebaseClient = null;

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
        Invitation invitation = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                FirebaseAuth.getInstance().getUid(), true);
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(email).set(invitation);


//        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
//        Query query = db.orderByChild("email").equalTo(email);
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        Log.i(TAG, "onDataChange: " + db.child(dataSnapshot.getKey()));
//                        db.child(dataSnapshot.getKey()).child("id").setValue(FirebaseAuth.getInstance().getUid());
//                }
//
//
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