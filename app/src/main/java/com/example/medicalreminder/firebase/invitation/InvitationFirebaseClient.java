package com.example.medicalreminder.firebase.invitation;

import android.content.Context;
import android.util.Log;

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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InvitationFirebaseClient implements InvitationFirebaseSource{
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("request_users");
    private Context context;
    private static InvitationFirebaseClient client = null;

    private InvitationFirebaseClient(Context context) {
        this.context = context;
    }

    public static InvitationFirebaseClient getClient(Context context) {
        if(client == null){
            client = new InvitationFirebaseClient(context);
        }
        return client;
    }

    @Override
    public void accept() {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Invitation invitation = task.getResult().toObject(Invitation.class);
                        String id = invitation.getId();
                        Invitation invitation1 = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),id, false);
                        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                                .set(invitation1);
                    }
                });

    }

    @Override
    public void deny() {
        Invitation invitation = new Invitation(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                null, false);
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .set(invitation);
    }
}
