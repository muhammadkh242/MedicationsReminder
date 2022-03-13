package com.example.medicalreminder.addhealthtracker.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addhealthtracker.presenter.TrackerPresenter;
import com.example.medicalreminder.addhealthtracker.presenter.TrackerPresenterInterface;
import com.example.medicalreminder.firebase.addhealthtracker.TrackerFirebaseClient;
import com.example.medicalreminder.model.healthtracker.TrackerRepo;


public class AddHealthTrackerFragment extends Fragment implements TrackerViewInterface {
    TrackerPresenterInterface presenter;

    Button inviteBtn;
    EditText trackerEdit;
    private static final String TAG = "TAG";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.healthtracker_screen, container, false);
        inviteBtn = view.findViewById(R.id.inviteBtn);
        trackerEdit = view.findViewById(R.id.trackerMail);

        presenter = new TrackerPresenter(TrackerRepo.getInstance(TrackerFirebaseClient
                .getTrackerFirebaseClient(getContext()),getContext()), this);


        inviteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = trackerEdit.getText().toString();
                Log.i(TAG, "onCreateView: " + email);
                Log.i(TAG, "onClick: " + inviteBtn.getText().toString());
                sendInvitation(email);
            }
        });
        return view;
    }


    @Override
    public void sendInvitation(String email) {
        presenter.sendInvitation(email);
    }


}