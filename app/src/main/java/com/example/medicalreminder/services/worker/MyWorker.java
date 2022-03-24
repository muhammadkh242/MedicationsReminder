package com.example.medicalreminder.services.worker;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.services.boardcast.MyReceiver;

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
        startBroadCast(inputData.getString("FIRST"));
        Log.i("TAG", "doWork: ");
        return Result.success();
    }

    public void startBroadCast(String name){
        Log.i("TAG", "startBroadCast: " + name);
        IntentFilter intentFilter = new IntentFilter("medDialog");
        getApplicationContext().registerReceiver(myReceiver,intentFilter);
        Intent in = new Intent();
        in.putExtra("FIRST", name);
        Log.i("TAG", "startBroadCast: " + name);
        in.setAction("medDialog");
        in.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getApplicationContext().sendBroadcast(in);
    }
}
