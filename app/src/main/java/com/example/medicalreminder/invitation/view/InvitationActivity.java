package com.example.medicalreminder.invitation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.firebase.invitation.InvitationFirebaseClient;
import com.example.medicalreminder.invitation.presenter.InvitationPresenter;
import com.example.medicalreminder.invitation.presenter.InvitationPresenterInterface;
import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.example.medicalreminder.model.invitation.InvitationRepo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class InvitationActivity extends AppCompatActivity implements InvitationViewInterface{

    Intent intent;
    String TAG = "TAG";

    InvitationPresenterInterface presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        presenter = new InvitationPresenter(InvitationRepo.getInstance(InvitationFirebaseClient.getClient(this),this),this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("invite you to be a health tracker")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG, "onDialogClick: Accept");
                        accept();

                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG, "onDialogClick: Deny");
                        deny();

                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                });
        builder.show();
    presenter = new InvitationPresenter(InvitationRepo.getInstance(InvitationFirebaseClient.getClient(this),
            this), this);

    }


    @Override
    public void accept() {
        presenter.accept();
    }

    @Override
    public void deny() {
        presenter.deny();

    }

}