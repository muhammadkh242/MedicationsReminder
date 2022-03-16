package com.example.medicalreminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.medicalreminder.addhealthtracker.view.InvitationService;
import com.example.medicalreminder.databinding.ActivityHomeBinding;
import com.example.medicalreminder.services.MyWorker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_med, R.id.navigation_healthtracker, R.id.navigation_seconduser)
                .build();
        //setSupportActionBar(binding.);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        binding.btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: Take");
                workManager(0);
            }
        });

        binding.btnSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: snooze");
                workManager(0);
            }
        });

    }

    private void workManager(int date) {
        // calc list
        String date2 = "16:20";

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = sdf.format(new Date());

        String[] dateSplit1 = currentDate.split(":");
        String[] dateSplit2 = date2.split(":");

        String dateH1 = dateSplit1[0];
        String dateM1 = dateSplit1[1];

        String dateH2 = dateSplit2[0];
        String dateM2 = dateSplit2[1];

        int diffH = Math.abs(Integer.parseInt(dateH1) - Integer.parseInt(dateH2));
        int diffM = Math.abs(Integer.parseInt(dateM1) - Integer.parseInt(dateM2));

        int delay = diffH >= 1 ? (diffH * 60) + diffM : diffH - diffM;

        Log.i("TAG", " :: " + delay);

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(delay, TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance().enqueue(workRequest);
    }

}