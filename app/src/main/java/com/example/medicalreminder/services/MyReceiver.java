package com.example.medicalreminder.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.displaymedication.view.DisplayDrugDetails;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, DisplayDrugDetails.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent1);
        context.unregisterReceiver(MyWorker.myReceiver);
    }
}
