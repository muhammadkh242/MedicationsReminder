package com.example.medicalreminder.remote.firebase.addhealthtracker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.invitation.Invitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String id = (String) task.getResult().get("id");
                Log.i(TAG, "onComplete: " + id);
                if(id != null){
                    Toast.makeText(context, email + " is tracking someone else", Toast.LENGTH_LONG).show();
                }
                else{
                    Invitation invitation = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                    FirebaseAuth.getInstance().getUid(), true, null);
                    CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
                    reference.document(email).set(invitation);
                    Toast.makeText(context, "Request has been sent", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}