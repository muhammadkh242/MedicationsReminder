package com.example.medicalreminder.dialogmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.medicalreminder.R;
import com.example.medicalreminder.services.worker.MyWorker;

import java.util.concurrent.TimeUnit;

public class DialogActivity extends AppCompatActivity {

    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        name = intent.getStringExtra("DETAIL");
        Log.i("TAG", "onCreate: " + name);
        showAlertDialogButtonClicked(name);
    }

    public void showAlertDialogButtonClicked(String name){
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.medication_reminder_dialog,
                        null);
        builder.setView(customLayout);
        ImageView imgSkip = customLayout.findViewById(R.id.imgSkip);
        imgSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this, "skip", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
            }
        });
        TextView txtName = customLayout.findViewById(R.id.txtDetailsDrug);
        txtName.setText(name);
        ImageView imgTake = customLayout.findViewById(R.id.imgSchedule);
        imgTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this, "Take", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
            }
        });
        ImageView imgSnooze = customLayout.findViewById(R.id.imgSnooze);
        imgSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = new Data.Builder().putString("DETAIL", "refo").build();
                OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setInitialDelay(20, TimeUnit.SECONDS)
                        .setInputData(data)
                        .build();

                Toast.makeText(DialogActivity.this, "snooze", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
            }
        });
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }
}