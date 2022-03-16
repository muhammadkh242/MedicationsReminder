package com.example.medicalreminder.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.databinding.FragmentHomeBinding;
import com.example.medicalreminder.home.presenter.HomeFragmentPresenter;
import com.example.medicalreminder.home.presenter.HomeFragmentPresenterInterface;
import com.example.medicalreminder.local.db.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.Repo;
import com.example.medicalreminder.model.addmedication.RepoInterface;
import com.example.medicalreminder.services.MyWorker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class HomeFragment extends Fragment implements HomeFragmentViewInterface, OnClickListenerHomeFragment{

    private FragmentHomeBinding binding;
    private HomeFragmentAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private HomeFragmentPresenterInterface presenter;
    private Calendar startTime = Calendar.getInstance();
    private Calendar endTime = Calendar.getInstance();
    private ArrayList datesToBeColored = new ArrayList();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        manager = new LinearLayoutManager(getContext());
        adapter = new HomeFragmentAdapter( getContext(), this);
        presenter = new HomeFragmentPresenter
                (getContext(), this, Repo.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
        binding.recycleViewHome.setLayoutManager(manager);
        binding.recycleViewHome.setAdapter(adapter);

        displayCalendar();
        binding.calendarView.setUpCalendar(startTime.getTimeInMillis(),
                endTime.getTimeInMillis(),
                datesToBeColored,
                new HorizontalCalendarView.OnCalendarListener() {
                    @Override
                    public void onDateSelected(String date) {
                        Log.i("TAG", "onDateSelected: " + date);

                        getMed(AddMedicationPresenter.formatCalenderDate(date));
                    }
                });
        return root;
    }

    private void displayCalendar(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        startTime.add(Calendar.MONTH, -6);
        endTime.add(Calendar.MONTH, 6);
        datesToBeColored.add(Tools.getFormattedDateToday());
        getMed(AddMedicationPresenter.formatCalenderDate(formatter.format(date)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showMed(LiveData<MedicationList> medList) {
        medList.observe((LifecycleOwner) getContext(), new Observer<MedicationList>() {
            @Override
            public void onChanged(MedicationList medicationList) {
//                Log.i("TAG", "onChanged: " + medicationList.getList().size());

               if (medicationList != null){
                   Comparator<MedicationDose> doseComparator = Comparator.comparing(MedicationDose::getHour);
                   Collections.sort(medicationList.getList(), doseComparator);
                   System.out.println("Sorting by Name");
                   adapter.setList(medicationList.getList());
                   adapter.notifyDataSetChanged();
               }else {
                   adapter.setList(new ArrayList<>());
                   adapter.notifyDataSetChanged();
               }
            }
        });
    }

    @Override
    public void getMed(String date) {
        presenter.getMedHome(date);
    }

    @Override
    public void onClick(String date) {

    }
}