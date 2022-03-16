package com.example.medicalreminder;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DialogFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        showAlertDialogButtonClicked();
        return inflater.inflate(R.layout.fragment_add_medication, container, false);

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
