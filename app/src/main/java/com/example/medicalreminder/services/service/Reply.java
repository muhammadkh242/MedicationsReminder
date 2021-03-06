package com.example.medicalreminder.services.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Reply extends Service {
    public static  final String CHANNEL_ID = "reply_id";

    CollectionReference myRef = FirebaseFirestore.getInstance().collection("Notifications");

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("TAG", "onStartCommand: on reply");

        if(FirebaseAuth.getInstance().getUid() != null){
            listenToReply();

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    public void listenToReply(){
        myRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.get("reply") != null){
                    Invitation invitation;
                    if(value.get("reply").equals("yes")){
                        showNotification("yes");
                        invitation = value.toObject(Invitation.class);
                        invitation.setReply(null);
                        myRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(invitation);
                    }
                    else if(value.get("reply").equals("no")){
                        showNotification("no");
                        invitation = value.toObject(Invitation.class);
                        invitation.setReply(null);
                        myRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(invitation);
                    }
                }

            }
        });
    }



    public void showNotification(String msg){
        String str = "";
        if(msg.equals("yes")){
            str = "Accepted";
        }
        else if(msg.equals("no")){
            str = "Rejected";
        }

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
                .setContentText("Request " + str)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true).build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(7, notification);
    }
}