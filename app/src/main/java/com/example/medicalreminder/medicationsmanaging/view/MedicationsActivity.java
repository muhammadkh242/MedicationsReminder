package com.example.medicalreminder.medicationsmanaging.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.medicalreminder.R;
import com.example.medicalreminder.medicationsmanaging.view.MedicationsFragment;

public class MedicationsActivity extends AppCompatActivity {
    MedicationsFragment medicationsFragment;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);

        //add fragment dynamically
        manager = getSupportFragmentManager();
        if(savedInstanceState == null){
            medicationsFragment = new MedicationsFragment();
            transaction = manager.beginTransaction();
            //inject fragment in container
//            transaction.add(R.id.fragmentContainerView, medicationsFragment, "med_frag");
//            transaction.commit();

        }
        else{
            medicationsFragment = (MedicationsFragment) manager.findFragmentByTag("med_frag");
        }
    }
}