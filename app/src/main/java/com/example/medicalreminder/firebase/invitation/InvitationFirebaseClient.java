package com.example.medicalreminder.firebase.invitation;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    public void accept(String id) {
        //get data for user that has the argument id and display it in second user list (medications)

    }

    @Override
    public void deny() {

    }
}
