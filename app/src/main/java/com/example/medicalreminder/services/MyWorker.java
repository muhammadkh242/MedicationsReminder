package com.example.medicalreminder.services;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

public class MyWorker extends Worker{

    public static MyReceiver myReceiver;

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        myReceiver = new MyReceiver();
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
       startBroadCast(inputData.getString("DETAIL"));
        Log.i("TAG", "doWork: ");
        return Result.success();
    }

    public void startBroadCast(String name){
        Log.i("TAG", "startBroadCast: ");
        IntentFilter intentFilter = new IntentFilter("medDialog");
        getApplicationContext().registerReceiver(myReceiver,intentFilter);
        Intent in = new Intent();
        in.putExtra("DETAIL", name);
        in.setAction("medDialog");
        in.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getApplicationContext().sendBroadcast(in);
    }
}
