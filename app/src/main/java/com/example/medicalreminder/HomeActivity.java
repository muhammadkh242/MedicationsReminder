package com.example.medicalreminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.medicalreminder.databinding.ActivityHomeBinding;
import com.example.medicalreminder.services.service.MyNotification;
import com.example.medicalreminder.services.service.Reply;
import com.example.medicalreminder.services.service.Take;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);


        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_med, R.id.navigation_healthtracker, R.id.navigation_seconduser)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        if(FirebaseAuth.getInstance().getUid() != null){

            startService(new Intent(this, MyNotification.class));
            startService(new Intent(this, Take.class));
            startService(new Intent(this, Reply.class));

        }
    }



}