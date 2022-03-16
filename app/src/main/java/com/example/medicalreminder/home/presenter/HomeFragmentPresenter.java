package com.example.medicalreminder.home.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.medicalreminder.home.view.HomeFragmentViewInterface;
import com.example.medicalreminder.local.db.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.RepoInterface;
import com.example.medicalreminder.services.MyWorker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface {


    private HomeFragmentViewInterface view;
    private RepoInterface repo;
    private Context context;
    private List<WorkRequest> requests;

    public HomeFragmentPresenter(Context context, HomeFragmentViewInterface view, RepoInterface repo) {
        this.context = context;
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getMedHome(String date) {
        LiveData<MedicationList> list = repo.getDrugs(date);
        view.showMed(list);
        callWork(list);

        Data data1 = new Data.Builder().putString("FIRST", "OUT").build();

        List<WorkRequest> requests = new ArrayList<>();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(data1)
                .build();
        WorkManager.getInstance().enqueue(workRequest);
//        OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(MyWorker.class)
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(data1)
//                .build();
//        requests.add(workRequest2);
//
//        WorkManager.getInstance().enqueue(requests);
    }

    private void callWork(LiveData<MedicationList> list) {
        list.observe((LifecycleOwner) context, new Observer<MedicationList>() {
            @Override
            public void onChanged(MedicationList medicationList) {
                if (medicationList != null) {
                    requests = new ArrayList<>();
                    Comparator<MedicationDose> doseComparator = Comparator.comparing(MedicationDose::getHour);
                    Collections.sort(medicationList.getList(), doseComparator);
                    System.out.println("Sorting by Name");
                    for (MedicationDose st : medicationList.getList()) {
                        Log.i("TAG", "onChanged: " + st.getHour() + " " + st.getName() + " ");
//                        calcDelay(st.getHour());
                    }
                    Log.i("TAG", "onChanged: listDB" + medicationList.getList().size());
                    Log.i("TAG", "onChanged: listWork" +requests.size());
//                    WorkManager.getInstance().enqueue(requests);
                }
            }
        });
    }

    private void calcDelay(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = sdf.format(new Date());
        String date2 = sdf.format(date);

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
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInitialDelay(delay, TimeUnit.MINUTES)
                .build();
        requests.add(workRequest);
    }
}
