package com.example.medicalreminder.services;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.medicalreminder.dialogmed.DialogActivity;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("DETAIL");
        Log.i("TAG", "onReceive: " + name);
        Intent intent1 = new Intent(context, DialogActivity.class);
        intent1.putExtra("DETAIL", name);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent1);
        context.unregisterReceiver(MyWorker.myReceiver);
    }
}
