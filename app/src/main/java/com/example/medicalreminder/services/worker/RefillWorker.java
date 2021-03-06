package com.example.medicalreminder.services.worker;

import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import com.example.medicalreminder.R;
import com.example.medicalreminder.invitation.view.InvitationActivity;
import com.example.medicalreminder.refillreminder.RfillDialogActivity;
import com.example.medicalreminder.services.boardcast.RefillReciever;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.refillreminder.RfillDialogActivity;
public class RefillWorker extends Worker {
    public static  final String CHANNEL_ID = "ch_id";
    public static RefillReciever RefillReciever;

    public RefillWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        RefillReciever = new RefillReciever();
    }

    @NonNull
    @Override
    public Result doWork() {

        String name = getInputData().getString("name");
        Intent notifyIntent = new Intent(getApplicationContext(), RfillDialogActivity.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                getApplicationContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        showNotification(name, notifyPendingIntent);
//        startBroadCast(name);

        return Result.success();
    }




    public void startBroadCast(String name){
        Log.i("TAG", "startBroadCast: RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        IntentFilter intentFilter = new IntentFilter("refillDialog");
        getApplicationContext().registerReceiver(RefillReciever,intentFilter);
        Intent outintent = new Intent();
        outintent.putExtra("name", name);
        outintent.setAction("refillDialog");
        outintent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getApplicationContext().sendBroadcast(outintent);
    }



//  @RequiresApi(api = Build.VERSION_CODES.Q)
    public void showNotification(String medName, PendingIntent intent){
        Intent notifyIntent = new Intent(getApplicationContext(), RfillDialogActivity.class);
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
                .setContentIntent(intent)
                .setAutoCancel(true).build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(2, notification);
    }
}
