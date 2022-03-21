package com.example.medicalreminder.services;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.example.medicalreminder.R;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.refillreminder.RefillDialog;

public class RefillWorker extends Worker {
    public static  final String CHANNEL_ID = "ch_id";


    public RefillWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String name = getInputData().getString("name");

        showNotification(name);


        return Result.success();
    }

//  @RequiresApi(api = Build.VERSION_CODES.Q)
    public void showNotification(String medName){
        Intent notifyIntent = new Intent(getApplicationContext(), RefillDialog.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                getApplicationContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        Log.i("TAG", "showNotification: ");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "channel";
            String desc = "desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(desc);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        android.app.Notification notification =  new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.drug)
                .setContentTitle("Refill Reminder")
                .setContentText(medName + " is about to end")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(notifyPendingIntent)
                .setAutoCancel(true).build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(2, notification);
    }
}
