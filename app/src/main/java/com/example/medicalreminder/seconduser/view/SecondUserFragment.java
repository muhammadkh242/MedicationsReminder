package com.example.medicalreminder.seconduser.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.databinding.FragmentHomeBinding;
import com.example.medicalreminder.databinding.FragmentSecondUserBinding;
import com.example.medicalreminder.model.Invitation;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.seconduser.SecondUserRepo;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenter;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenterInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class SecondUserFragment extends Fragment implements SecondUserViewInterface{

    FragmentSecondUserBinding binding;

    SecondAdapter adapter;
    LinearLayoutManager layoutManager;

    Calendar start;
    Calendar end;
    ArrayList<String> dates = new ArrayList<>();

    SecondUserPresenterInterface presenter;

    public SecondUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new SecondAdapter(getContext());

        presenter = new SecondUserPresenter(this, SecondUserRepo.getRepo(getContext()));
        binding.recyclerSecond.setLayoutManager(layoutManager);
        binding.recyclerSecond.setAdapter(adapter);

        start = Calendar.getInstance();
        end = Calendar.getInstance();

        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String id = (String) task.getResult().get("id");
                if(id == null){
                    Toast.makeText(getContext(), "NO tracked users", Toast.LENGTH_LONG).show();
                }
                else{
                    displayCalendar();
                    binding.calendarSecond.setUpCalendar(start.getTimeInMillis(), end.getTimeInMillis(), dates, new HorizontalCalendarView.OnCalendarListener() {
                        @Override
                        public void onDateSelected(String date) {
                            Log.i("TAG", "onDateSelected: " + date);
                            getMeds(AddMedicationPresenter.formatCalenderDate(date));

                        }
                    });

                }
            }
        });


        return root;
    }

    private void displayCalendar(){
        Log.i("TAG", "displayCalendar: SECOND ONE");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, 6);
        dates.add(Tools.getFormattedDateToday());
        getMeds(AddMedicationPresenter.formatCalenderDate(formatter.format(date)));
    }


    @Override
    public void showData(MutableLiveData<List<MedicationList>> medList) {

        medList.observe((LifecycleOwner) getContext(), new Observer<List<MedicationList>>() {
            @Override
            public void onChanged(List<MedicationList> medicationLists) {
                if (medicationLists.size() != 0) {
                    List<MedicationDose> list = new ArrayList<>();

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
                    adapter.setData(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

    public void getMeds(String date){
        presenter.getMeds(date);
    }
}