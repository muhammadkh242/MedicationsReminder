package com.example.medicalreminder.seconduser.view;
import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.databinding.FragmentSecondUserBinding;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.seconduser.SecondUserRepo;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenter;
import com.example.medicalreminder.seconduser.presenter.SecondUserPresenterInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import in.akshit.horizontalcalendar.HorizontalCalendarView;
import in.akshit.horizontalcalendar.Tools;

public class SecondUserFragment extends Fragment implements SecondUserViewInterface, OnClickListenerSecondUser{

    FragmentSecondUserBinding binding;

    SecondAdapter adapter;
    LinearLayoutManager layoutManager;

    Calendar start;
    Calendar end;
    ArrayList<String> dates = new ArrayList<>();

    SecondUserPresenterInterface presenter;

    AlertDialog dialog;

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
        adapter = new SecondAdapter(getContext(), this);

        presenter = new SecondUserPresenter(this, SecondUserRepo.getRepo(getContext()));
        binding.recyclerSecond.setLayoutManager(layoutManager);
        binding.recyclerSecond.setAdapter(adapter);


        binding.delBtn.setEnabled(false);
        binding.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFriend();
            }
        });


        start = Calendar.getInstance();
        end = Calendar.getInstance();

        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String id = (String) task.getResult().get("id");
                if(id == null){
                    Toast.makeText(getContext(), "NO tracked users", Toast.LENGTH_SHORT).show();
                }
                else{

                    displayCalendar();
                    binding.calendarSecond.setUpCalendar(start.getTimeInMillis(), end.getTimeInMillis(), dates, new HorizontalCalendarView.OnCalendarListener() {
                        @Override
                        public void onDateSelected(String date) {
                            Log.i("TAG", "onDateSelected: " + date);
                            getMeds(CalculationMedication.formatCalenderDate(date));

                        }
                    });

                    binding.delBtn.setEnabled(true);

                }
            }
        });


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Notifications");
        reference.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String id = (String) task.getResult().get("id");
                if(id != null){
                    displayCalendar();
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void displayCalendar(){
        Log.i("TAG", "displayCalendar: SECOND ONE");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        start.add(Calendar.MONTH, -6);
        end.add(Calendar.MONTH, 6);
        dates.add(Tools.getFormattedDateToday());
        getMeds(CalculationMedication.formatCalenderDate(formatter.format(date)));
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

    @Override
    public void deleteFriend() {
        presenter.deleteFriend();
    }

    @Override
    public void takeFriendPill(String name) {
        presenter.takeFriendPill(name);
    }

    @Override
    public void onClick(MedicationDose dose) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View myDialogView = getLayoutInflater().inflate(R.layout.friend_take_dialog, null);
        builder.setView(myDialogView);

        TextView medName = myDialogView.findViewById(R.id.medName);
        TextView timeTxt = myDialogView.findViewById(R.id.timeTxt);
        ImageView takeIcon = myDialogView.findViewById(R.id.takeIcon);
        ImageView closeIcon = myDialogView.findViewById(R.id.closeIcon);

        medName.setText(dose.getName());
        timeTxt.setText(dose.getHour());

        takeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //reduce total pills for your friend
                takeFriendPill(dose.getName());
                dialog.cancel();

            }
        });

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog = builder.create();
        dialog.show();


    }
}