package com.example.medicalreminder.home.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.databinding.FragmentHomeBinding;
import com.example.medicalreminder.home.presenter.HomeFragmentPresenter;
import com.example.medicalreminder.home.presenter.HomeFragmentPresenterInterface;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.Repo;
import com.example.medicalreminder.model.addmedication.RepoInterface;
import com.example.medicalreminder.model.home.RepoHome;
import com.example.medicalreminder.model.meddialog.RepoDialog;
import com.example.medicalreminder.services.MyNewWorker;
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

public class HomeFragment extends Fragment implements HomeFragmentViewInterface, OnClickListenerHomeFragment {

    private FragmentHomeBinding binding;
    private HomeFragmentAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private HomeFragmentPresenterInterface presenter;
    private Calendar startTime = Calendar.getInstance();
    private Calendar endTime = Calendar.getInstance();
    private AlertDialog dialog;
    private ArrayList datesToBeColored = new ArrayList();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        manager = new LinearLayoutManager(getContext());
        adapter = new HomeFragmentAdapter(getContext(), this);
        presenter = new HomeFragmentPresenter
                (getContext(), this, Repo.getInstance(getContext(),
                        ConcreteLocalSource.getInstance(getContext())),
                        RepoDialog.getInstance(getContext()),
                        RepoHome.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
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
        OneTimeWorkRequest newRequest = new OneTimeWorkRequest.Builder(MyNewWorker.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(getContext()).enqueue(newRequest);
        OneTimeWorkRequest newRequest1 = new OneTimeWorkRequest.Builder(MyNewWorker.class)
                .setInitialDelay(20, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(getContext()).enqueue(newRequest1);

        return root;
    }

    private void displayCalendar() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        startTime.add(Calendar.MONTH, -1);
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
                if (medicationList != null) {
                    Comparator<MedicationDose> doseComparator = Comparator.comparing(MedicationDose::getHour);
                    Collections.sort(medicationList.getList(), doseComparator);
                    System.out.println("Sorting by Name");
                    adapter.setList(medicationList.getList());
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.setList(new ArrayList<>());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void showMedOnline(MutableLiveData<List<MedicationList>> medList) {
        medList.observe((LifecycleOwner) getContext(), new Observer<List<MedicationList>>() {
            @Override
            public void onChanged(List<MedicationList> medicationLists) {
                Log.i("TAG", "onChanged: " + medicationLists.size());
                if (medicationLists.size() != 0) {
                    List<MedicationDose> list = new ArrayList<>();;
                    MedicationDose dose = new MedicationDose();
                    List<MedicationDose> doseList = new ArrayList<>();
                    for (int i = 0; i < medicationLists.size(); i++) {
                        doseList = medicationLists.get(i).getList();
                        for (int j = 0; j < doseList.size(); j++) {
                            dose = doseList.get(j);
                            list.add(dose);
                        }
                    }
                    Comparator<MedicationDose> doseComparator = Comparator.comparing(MedicationDose::getHour);
                    Collections.sort(list, doseComparator);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.i("TAG", "onChanged: else ");
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
    public void getDrugRealTime(String name) {
        presenter.getDrugRealTime(name);
    }
    @Override
    public void onClick(MedicationDose dose) {
        showAlertDialogButtonClicked(dose);
    }

    public void showAlertDialogButtonClicked(MedicationDose dose) {
        AlertDialog.Builder builder
                = new AlertDialog.Builder(getContext());
        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.medication_reminder_dialog,
                        null);
        builder.setView(customLayout);
        TextView txtName = customLayout.findViewById(R.id.txtDrugName);
        TextView txtTime = customLayout.findViewById(R.id.txtSchedule);
        TextView txtTake = customLayout.findViewById(R.id.txtDetailsDrug);
        txtName.setText(dose.getName());
        txtTime.setText(dose.getHour());
        ImageView imgSkip = customLayout.findViewById(R.id.imgSkip);
        imgSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "skip", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
            }
        });
        ImageView imgTake = customLayout.findViewById(R.id.imgCheckTake);
        imgTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Take", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
                getDrugRealTime(dose.getName());
                dialog.cancel();

            }
        });
        ImageView imgSnooze = customLayout.findViewById(R.id.imgSnooze);
        imgSnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "snooze", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onClick: ");
            }
        });
        dialog = builder.create();

        dialog.show();
    }
}