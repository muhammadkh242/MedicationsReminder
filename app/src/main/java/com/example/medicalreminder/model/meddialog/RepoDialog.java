package com.example.medicalreminder.model.meddialog;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.services.MyNewWorker;
import com.example.medicalreminder.services.MyWorker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RepoDialog implements RepoDialogInterface {

    private static RepoDialog repo;
    private Context context;
    private List<WorkRequest> requests;
    private int delay;


    private RepoDialog(Context context) {
        this.context = context;
    }

    public static RepoDialog getInstance(Context context) {
        if (repo == null) {
            repo = new RepoDialog(context);
        }
        return repo;
    }

    @Override
    public void calcWork(LiveData<MedicationList> list) {
        list.observe((LifecycleOwner) context, new Observer<MedicationList>() {
            @Override
            public void onChanged(MedicationList medicationList) {
                if (medicationList != null) {
                    requests = new ArrayList<>();
                    Comparator<MedicationDose> doseComparator = Comparator.comparing(MedicationDose::getHour);
                    Collections.sort(medicationList.getList(), doseComparator);
                    System.out.println("Sorting by Name");
                    for (int i = 0; i < medicationList.getList().size(); i++) {
                        Log.i("TAG", "onChanged: " +
                                medicationList.getList().get(i).getHour() + " " +
                                medicationList.getList().get(i).getName() + " ");
                        delay = calcDelay(medicationList.getList().get(i).getHour());
                        Data data = new Data.Builder().putString("FIRST",
                                String.valueOf(medicationList.getList().get(i).getName())).build();
                        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                                .setInitialDelay(delay, TimeUnit.MINUTES)
                                .setInputData(data)
                                .build();
                        requests.add(workRequest);
                    }
                    Log.i("TAG", "onChanged: listDB" + medicationList.getList().size());
                    Log.i("TAG", "onChanged: listWork" + requests.size());
                    WorkManager.getInstance().enqueue(requests);

                }
            }
        });


    }

    @Override
    public int calcDelay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = sdf.format(new Date());

        Log.i("TAG", "calcDelay: " + currentDate);
        String date2 = null;
        try {
            date2 = sdf.format(sdf.parse(date));
            Log.i("TAG", "calcDelay: " + date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] dateSplit1 = currentDate.split(":");
        String[] dateSplit2 = date2.split(":");

        String dateH1 = dateSplit1[0];
        String dateM1 = dateSplit1[1];

        String dateH2 = dateSplit2[0];
        String dateM2 = dateSplit2[1];

        int diffH = Math.abs(Integer.parseInt(dateH1) - Integer.parseInt(dateH2));
        int diffM = Math.abs(Integer.parseInt(dateM1) - Integer.parseInt(dateM2));

        int delay = diffH >= 1 ? (diffH * 60) + diffM : diffH + diffM;

        Log.i("TAG", " :: " + delay);
        return delay;

    }
}
