package com.example.medicalreminder.services.boardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.medicalreminder.dialogmed.DialogActivity;
import com.example.medicalreminder.services.worker.MyWorker;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("FIRST");
        Log.i("TAG", "onReceive: " + name);
        Intent intent1 = new Intent(context, DialogActivity.class);
        intent1.putExtra("FIRST", name);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent1);
//        context.unregisterReceiver(MyWorker.myReceiver);
    }
}
