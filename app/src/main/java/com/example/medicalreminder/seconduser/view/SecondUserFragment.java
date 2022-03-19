package com.example.medicalreminder.seconduser.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
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

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.seconduser.SecondUserRepo;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenter;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class SecondUserFragment extends Fragment implements SecondUserViewInterface{

    RecyclerView secondRecycler;
    List<UserMed> medList = new ArrayList<>();
    SecondAdapter adapter;
    LinearLayoutManager layoutManager;
    HorizontalCalendarView calendarView;

    Calendar start;
    Calendar end;
    ArrayList<String> dates = new ArrayList<>();

    SecondUserPresenterInterface presenter;
    Calendar calendar;
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

        View view =inflater.inflate(R.layout.fragment_second_user, container, false);
        secondRecycler = view.findViewById(R.id.recycler_second);
        secondRecycler.setHasFixedSize(true);
        adapter = new SecondAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        secondRecycler.setLayoutManager(layoutManager);
        secondRecycler.setAdapter(adapter);

        start = Calendar.getInstance();
        end = Calendar.getInstance();

        calendarView = view.findViewById(R.id.calendar_second);

        displayCalendar();
        calendarView.setUpCalendar(start.getTimeInMillis(), end.getTimeInMillis(), dates, new HorizontalCalendarView.OnCalendarListener() {
            @Override
            public void onDateSelected(String date) {
                Log.i("TAG", "onDateSelected: " + date);
            }
        });

        presenter = new SecondUserPresenter(this, SecondUserRepo.getRepo(getContext()));
        //go fetch data
        getMeds();

        return view;
    }

    private void displayCalendar(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, 6);
        dates.add(Tools.getFormattedDateToday());
//        getMed(AddMedicationPresenter.formatCalenderDate(formatter.format(date)));
    }


    @Override
    public void showData(MutableLiveData<List<MedicationList>> medList) {
        //this method will be called from presenter
        //observe data here


    }

    public void getMeds(){
        presenter.getMeds();
    }
}