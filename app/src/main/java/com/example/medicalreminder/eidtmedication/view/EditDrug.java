package com.example.medicalreminder.eidtmedication.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.medicalreminder.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EditDrug extends AppCompatActivity {


    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drug);

        CharSequence name = getString(R.string.date);
        String description = getString(R.string.days);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("1", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        //showNotif();
        /*scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                showNotif();

            }
        }, 3, 3, TimeUnit.SECONDS);*/

    }
         //showAlertDialogButtonClicked();

//       arrow.setOnClickListener(view -> {
//            if(hiddenGroup.getVisibility() == View.VISIBLE){
//                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                hiddenGroup.setVisibility(View.GONE);
//                textView.setVisibility(View.VISIBLE);
//                arrow.setImageResource(android.R.drawable.arrow_down_float);
//            }
//            else {
//                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
//                textView.setVisibility(View.GONE);
//                hiddenGroup.setVisibility(View.VISIBLE);
//                arrow.setImageResource(android.R.drawable.arrow_up_float);
//            }
//        });
//
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
//                if(ischecked){
//                    if(getHiddenGroup.getVisibility() == View.GONE){
//                        TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
//                        getHiddenGroup.setVisibility(View.VISIBLE);
//                        textView2.setVisibility(View.GONE);
//                        //arrow.setImageResource(android.R.drawable.arrow_down_float);
//                    }
//                }
//                else {
//                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
//                    textView2.setVisibility(View.VISIBLE);
//                    getHiddenGroup.setVisibility(View.GONE);
//
//                    //arrow.setImageResource(android.R.drawable.arrow_up_float);
//                }
//            }
//        });



   /* public void showNotif(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),"1")
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle("message")
                .setContentText("hello")
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(4, notification.build());

    }*/
    public void showAlertDialogButtonClicked(){
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.medication_reminder_dialog,
                        null);
        builder.setView(customLayout);
        ImageView imageView = customLayout.findViewById(R.id.imgClose);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditDrug.this, "skip", Toast.LENGTH_SHORT).show();
            }
        });
                AlertDialog dialog
                = builder.create();
        dialog.show();
    }
}