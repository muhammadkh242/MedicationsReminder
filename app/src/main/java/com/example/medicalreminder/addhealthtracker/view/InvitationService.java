package com.example.medicalreminder.addhealthtracker.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.healthtracker.RequestUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InvitationService extends Service {
    private static final String TAG = "TAG";
    public static  final String CHANNEL_ID = "id";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable r = new Runnable() {
            @Override
            public void run() {

                readData();
            }
        };
        Thread thread = new Thread(r);
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    //Read Data From Firebase DataBase
    public void readData(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("request_users");
        Query query = db.orderByChild("userID").equalTo(FirebaseAuth.getInstance().getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        RequestUser user = dataSnapshot.getValue(RequestUser.class);
                        Log.i(TAG, "onDataChange: " + user.isRequest() + " : " + user.getUserID());
                        if(user.isRequest() == true){
                            showNotification();
                        }
                        else{
                            Log.i(TAG, "onDataChange: no request");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void showNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel);
            String desc = getString(R.string.channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        Notification notification =  new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Invitation")
                .setContentText("Invitation to be a HealthTracker")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true).build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, notification);
    }

}
