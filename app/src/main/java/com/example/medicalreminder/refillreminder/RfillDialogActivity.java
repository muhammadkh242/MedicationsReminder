package com.example.medicalreminder.refillreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.medicalreminder.R;

public class RfillDialogActivity extends AppCompatActivity {
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfill_dialog);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

    }
}