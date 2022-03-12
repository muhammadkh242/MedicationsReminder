package com.example.medicalreminder.addhealthtracker.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.medicalreminder.R;
//import com.example.medicalreminder.firebase.healthtracker.HealthTrackersClient;
//import com.example.medicalreminder.model.healthtracker.HealthTrackerUser;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;

//fragment send email from edit text to invitation service

public class AddHealthTrackerFragment extends Fragment {
    Button inviteBtn;
    EditText trackerEdit;
    private static final String TAG = "TAG";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HealthTrackerUser healthTrackerUser = new HealthTrackerUser("20", null, false, "mo@gmail");
//        DatabaseReference databaseReference = HealthTrackersClient.getDatabaseReference();
//        databaseReference.push().setValue(healthTrackerUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.healthtracker_screen, container, false);
        inviteBtn = view.findViewById(R.id.inviteBtn);
        trackerEdit = view.findViewById(R.id.trackerMail);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String trackerEmail = trackerEdit.getText().toString();
        inviteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendInvitation(trackerEmail);
                Log.i(TAG, "Edit Text: " + trackerEmail);
            }
        });
    }

    //send invitation
//    public void sendInvitation(String email){
//        DatabaseReference databaseReference = HealthTrackersClient.getDatabaseReference();
//        Query query = databaseReference.orderByChild("userEmail");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        HealthTrackerUser user = dataSnapshot.getValue(HealthTrackerUser.class);
//                        if(user.getUserEmail().equals(email)){
//                            Toast.makeText(getContext(), "user found", Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                }
//                else{
//                    Toast.makeText(getContext(), "User not found" , Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

}