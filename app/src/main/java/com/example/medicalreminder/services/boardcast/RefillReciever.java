package com.example.medicalreminder.services.boardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.medicalreminder.refillreminder.RfillDialogActivity;

import com.example.medicalreminder.services.worker.RefillWorker;

public class RefillReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getStringExtra("name");
        Intent outintent = new Intent(context, RfillDialogActivity.class);
        outintent.putExtra("name", name);
        outintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(outintent);
        context.unregisterReceiver(RefillWorker.RefillReciever);


    }
}
