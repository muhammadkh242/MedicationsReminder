package com.example.medicalreminder.eidtmedication.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.databinding.ActivityEditDrugBinding;
import com.example.medicalreminder.eidtmedication.presenter.EditMedicationPresenter;
import com.example.medicalreminder.eidtmedication.presenter.EditMedicationPresenterInterface;
import com.example.medicalreminder.local.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.editmedication.RepoEdit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class EditDrug extends AppCompatActivity  implements EditMedicationViewInterface{

    EditMedicationPresenterInterface editMedPI;
    ActivityEditDrugBinding binding;
    private int mYear, mMonth, mDay;
    Medication medication;
    List<String> days;
    List<String> weeks;
    List<String> list;
    boolean flag = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditDrugBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        medication = Medication.getInstance();

        editMedPI = EditMedicationPresenter.getInstance(getApplicationContext()
                , RepoEdit.getInstance(getApplicationContext(),
                        ConcreteLocalSource.getInstance(getApplicationContext())));
        days = new ArrayList<>();
        weeks = new ArrayList<>();
        list = new ArrayList<>();

        list.add("......");
        list.add("30 days");
        list.add("1 week");
        list.add("10 days");
        list.add("5 days");

        weeks.add(".....");
        weeks.add("Once week");
        weeks.add("Twice week");
        weeks.add("3 times in week");
        weeks.add("4 times in week");

        days.add("......");
        days.add("Once day");
        days.add("Twice day");
        days.add("3 times in day");
        days.add("4 times in day");

        ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(ad);

        binding.editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });
        binding.editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimePicker();
            }
        });
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
             if(selectedId==binding.radioDay.getId()){
                 medication.setEveryDayOr("Yes");
                 ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, days);
                 ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 binding.spinnerDuration.setAdapter(ad);
            }
             else {
                 // weeks
                 medication.setEveryDayOr("No");
                 flag = true;
                 ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, weeks);
                 ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 binding.spinnerDuration.setAdapter(ad);
             }
        }
        });
        binding.spinnerDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(flag){
                    String text = adapterView.getItemAtPosition(i).toString();
                    medication.setTimesInWeeks(text);
                }
                else{
                    String text = adapterView.getItemAtPosition(i).toString();
                    medication.setTimesInday(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                medication.setDurationDrug(text);
                medication.setName(binding.edtmedname.getText().toString());
                CalculationMedication.calDuration();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDrug();
            }
        });
}
    private void displayDatePicker(){
        final Calendar calendar = Calendar.getInstance ();
        mYear = calendar.get ( Calendar.YEAR );
        mMonth = calendar.get ( Calendar.MONTH );
        mDay = calendar.get ( Calendar.DAY_OF_MONTH );

        //show dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog ( this, new DatePickerDialog.OnDateSetListener () {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.editDate.setText(dayOfMonth +"-"+(month+1)+"-"+year);
                medication.setFirstDateDose(dayOfMonth+"-"+(month+1)+"-"+year);

            }
        }, mYear, mMonth, mDay );
        datePickerDialog.show ();
    }
    private void displayTimePicker(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                binding.editTime.setText(selectedHour +":"+selectedMinute);
                medication.setFirstTimeDose(selectedHour+":"+selectedMinute);
                CalculationMedication.calListHour();

            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @Override
    public void editDrug() {
     editMedPI.deleteDrugFirestore(medication);
    }
}