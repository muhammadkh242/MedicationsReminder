package com.example.medicalreminder.seconduser.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.medicalreminder.R;
import com.example.medicalreminder.medicationsmanaging.view.MedicationsFragment;

public class SecondUserActivity extends AppCompatActivity {

    SecondUserFragment secondUserFragment;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_user);

        //  setContentView(R.layout.activity_main);

        //add fragment dynamically
        manager = getSupportFragmentManager();
        if(savedInstanceState == null){
            secondUserFragment = new SecondUserFragment();
            transaction = manager.beginTransaction();

        }
        else{
            secondUserFragment = (SecondUserFragment) manager.findFragmentByTag("second_frag");
        }
    }
}