package com.example.medicalreminder.displaymedication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.UserMed;

public class DisplayDrugDetails extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_drug_details);
        showAlertDialogButtonClicked();

        UserMed userMed = (UserMed) getIntent().getSerializableExtra("userMed");
        Log.i("TAG", " SERIALIZABLE TEST : " + userMed.getName());
    }

    public void showAlertDialogButtonClicked(){
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.medication_reminder_dialog,
                        null);
        builder.setView(customLayout);
        ImageView imageView = customLayout.findViewById(R.id.imgClose);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(EditDrug.this, "skip", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
            }
        });
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }
}