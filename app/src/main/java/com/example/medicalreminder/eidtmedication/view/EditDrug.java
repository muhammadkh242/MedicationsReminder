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
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.ActivityDisplayDrugDetailsBinding;
import com.example.medicalreminder.databinding.ActivityEditDrugBinding;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EditDrug extends AppCompatActivity {


    ActivityEditDrugBinding binding;
    Medication medication;
    Drug drug;
    ArrayList<String> times;
    ArrayAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditDrugBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        medication = Medication.getInstance();
        drug = new Drug();

        times = new ArrayList<>();
        times.add("Once day");
        times.add("Twice day");
        times.add("3 times in day");
        times.add("4 times in day");
        times.add("Once week");
        times.add("Twice week");
        times.add("3 times in week");
        times.add("4 times in week");


//        binding.cardviewMedicationName.cardviewReminderTimes.imgCardviewReminderTimesShow.
//                setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if( binding.cardviewMedicationName.cardviewReminderTimes.cardvieReminderTimesGroup.getVisibility()
//                        == View.VISIBLE){
//                    TransitionManager.beginDelayedTransition(
//                            binding.cardviewMedicationName.cardviewReminderTimes
//                            , new AutoTransition());
//                    binding.cardviewMedicationName.cardviewMedicationNameGroup.setVisibility(View.GONE);
//                    binding.cardviewMedicationName.txtMedicationName.setVisibility(View.VISIBLE);
//                    binding.cardviewMedicationName.imgCardviewMedicationTimes.setImageResource(android.R.drawable.arrow_down_float);
//                }
//                else {
//                    TransitionManager.beginDelayedTransition(binding.cardviewMedicationName.medicationTimesCardview,
//                            new AutoTransition());
//                    binding.cardviewMedicationName.txtMedicationName.setVisibility(View.GONE);
//                    binding.cardviewMedicationName.cardviewMedicationNameGroup.setVisibility(View.VISIBLE);
//                    binding.cardviewMedicationName.imgCardviewMedicationTimes.setImageResource(android.R.drawable.arrow_up_float);
//                }
//            }
//        });

//        medication.setName(binding.cardviewMedicationName.edtDrugName.getText().toString());
//
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,times);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.cardviewMedicationName.cardviewReminderTimes.spinner.setAdapter(aa);
//        binding.cardviewMedicationName.cardviewReminderTimes.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }


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
    }
}