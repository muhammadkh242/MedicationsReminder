package com.example.medicalreminder.dialogmed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.eidtmedication.view.EditDrug;

public class DialogFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_invitation, container, false);
        showAlertDialogButtonClicked();
        return view;

    }

    public void showAlertDialogButtonClicked(){
        AlertDialog.Builder builder
                = new AlertDialog.Builder(getContext());

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.medication_reminder_dialog,
                        null);
        builder.setView(customLayout);
        ImageView imgSkip = customLayout.findViewById(R.id.imgSkip);
        ImageView imgSchedule = customLayout.findViewById(R.id.imgSchedule);
        ImageView imgSnooze = customLayout.findViewById(R.id.imgSnooze);
        imgSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "skip", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
        imgSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "schedule", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
        imgSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "snooze", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }
}
