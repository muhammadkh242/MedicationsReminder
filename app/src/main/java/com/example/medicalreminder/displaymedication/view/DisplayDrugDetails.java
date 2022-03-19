package com.example.medicalreminder.displaymedication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.ActivityDisplayDrugDetailsBinding;
import com.example.medicalreminder.databinding.ActivityInvitationBinding;
import com.example.medicalreminder.eidtmedication.view.EditDrug;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.Drug;

public class DisplayDrugDetails extends AppCompatActivity {

    private ActivityDisplayDrugDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_drug_details);




        binding = ActivityDisplayDrugDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Drug drug = (Drug) getIntent().getSerializableExtra("userMed");

        binding.txtNameDrug.setText(drug.getName());
        binding.txtQuantityDrug.setText(drug.getForm());
        binding.txtReminderDetails.setText(drug.getTimesInDays());
        binding.txtReminderTime.setText(drug.getDurationDrug());
        binding.txtRefill.setText(String.valueOf(drug.getRefill()));

        binding.imagEditDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayDrugDetails.this, EditDrug.class);
                startActivity(intent);
            }
        });
    }
}