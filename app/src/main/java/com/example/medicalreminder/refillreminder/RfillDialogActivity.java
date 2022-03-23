package com.example.medicalreminder.refillreminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalreminder.R;
import com.example.medicalreminder.remote.firestore.seconduser.SecondUserFirebaseClient;

public class RfillDialogActivity extends AppCompatActivity {
    private String name;
    AlertDialog dialog;
    SecondUserFirebaseClient secondUserFirebaseClient = new SecondUserFirebaseClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refill_dialog);

        Intent incomingintent = getIntent();
        name = incomingintent.getStringExtra("name");
        showDialog(name);

    }

    public void showDialog(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogLayout = getLayoutInflater().inflate(R.layout.refill_dialog, null);
        builder.setView(dialogLayout);

        ImageView refillIcon = dialogLayout.findViewById(R.id.refillIcon);
        ImageView closeIcon = dialogLayout.findViewById(R.id.closeIcon);

        TextView medName = dialogLayout.findViewById(R.id.medName);
        medName.setText(name);
        EditText refillEdit = dialogLayout.findViewById(R.id.refillEdt);


        refillIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(refillEdit.getText().toString() == null || Integer.valueOf(refillEdit.getText().toString()) == null){
                    Toast.makeText(RfillDialogActivity.this, "Please Enter Valid Refill Number", Toast.LENGTH_SHORT).show();
                }
                else{
                    //call refill method
                    int num = Integer.valueOf(refillEdit.getText().toString());
                    secondUserFirebaseClient.setRefill(name, num);
                }
            }
        });

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
}