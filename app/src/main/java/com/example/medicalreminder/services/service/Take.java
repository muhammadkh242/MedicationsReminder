package com.example.medicalreminder.services.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.invitation.Invitation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Take extends Service {
    public static  final String CHANNEL_ID = "myyyyid";

    public Take() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG", "onStartCommand: TAKE SERVICE");
        listen();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    public void listen(){
        Log.i("TAG", "listen: ");
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.get("take") != null){
                    String user = (String) value.get("take");
                    Log.i("TAG", "onEvent: take" + value.get("take"));
                    Invitation invitation = value.toObject(Invitation.class);
                    invitation.setTake(null);
                    reference.document(user).set(invitation);
                    showNotification(user);

                }
            }
        });
    }


    public void showNotification(String user){

        Log.i("TAG", "showNotification: ");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel);
            String desc = getString(R.string.channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        android.app.Notification notification =  new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Invitation")
                .setContentText(user + " invite you to be a HealthTracker")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true).build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, notification);
    }

}