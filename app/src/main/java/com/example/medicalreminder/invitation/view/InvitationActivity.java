package com.example.medicalreminder.invitation.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.remote.firestore.invitation.InvitationFirebaseClient;
import com.example.medicalreminder.invitation.presenter.InvitationPresenter;
import com.example.medicalreminder.invitation.presenter.InvitationPresenterInterface;
import com.example.medicalreminder.model.invitation.InvitationRepo;

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