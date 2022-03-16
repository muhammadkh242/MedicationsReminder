package com.example.medicalreminder.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.DialogFragment;
import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.eidtmedication.view.EditDrug;

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
        if(!inputData.getString("FIRST").equals("IN")){
            startBroadCast();
        }
        Log.i("TAG", "doWork: ");
        return Result.success();
    }

    public void startBroadCast(){
        Log.i("TAG", "startBroadCast: ");
        IntentFilter intentFilter = new IntentFilter("medDialog");
        getApplicationContext().registerReceiver(myReceiver,intentFilter);
        Intent in = new Intent();
        in.setAction("medDialog");
        in.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        getApplicationContext().sendBroadcast(in);
    }

    private void notification(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle("title")
                .setContentText("task")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(1, notification.build());
    }

    public void showAlertDialogButtonClicked(){
//        AlertDialog.Builder builder
//                = new AlertDialog.Builder(getApplicationContext());
//
//        // set the custom layout
//        final View customLayout
//                = getLayoutInflater()
//                .inflate(
//                        R.layout.medication_reminder_dialog,
//                        null);
//        builder.setView(customLayout);
//        ImageView imageView = customLayout.findViewById(R.id.imgClose);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(EditDrug.this, "skip", Toast.LENGTH_SHORT).show();
//                Log.i("TAG", "onClick: ");
//            }
//        });
//        AlertDialog dialog
//                = builder.create();
//        dialog.show();
    }
}
