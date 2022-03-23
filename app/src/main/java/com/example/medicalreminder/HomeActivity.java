package com.example.medicalreminder;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medicalreminder.addMedication.view.AddMedFragment;
import com.example.medicalreminder.addhealthtracker.view.AddHealthTrackerFragment;
import com.example.medicalreminder.databinding.ActivityHomeBinding;
import com.example.medicalreminder.home.view.HomeFragment;
import com.example.medicalreminder.medicationsmanaging.view.MedicationsFragment;
import com.example.medicalreminder.seconduser.view.SecondUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        binding.navView.setOnNavigationItemSelectedListener(this);
//        binding.navView.setSelectedItemId(R.id.navigation_home);


//        BottomNavigationView navView = findViewById(R.id.nav_view);
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_add, R.id.navigation_med, R.id.navigation_healthtracker, R.id.navigation_seconduser)
//                .build();
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    }

    HomeFragment homeFragment = new HomeFragment();
    AddMedFragment addMedFragment = new AddMedFragment();
    MedicationsFragment medicationsFragment = new MedicationsFragment();
    AddHealthTrackerFragment healthTrackerFragment = new AddHealthTrackerFragment();
    SecondUserFragment secondUserFragment = new SecondUserFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;

            case R.id.navigation_add:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, addMedFragment).commit();
                return true;

            case R.id.navigation_med:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, medicationsFragment).commit();
                return true;
            case R.id.navigation_healthtracker:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, healthTrackerFragment).commit();
                return true;
            case R.id.navigation_seconduser:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, secondUserFragment).commit();
                return true;
        }
        return false;
    }



}