package com.example.medicalreminder.addMedication.presenter;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.MedicationDose;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.RepoInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddMedicationPresenter implements AddMedicationPresenterInterface{

    Context context;
    private static AddMedicationPresenter addMedicationPresenter;
    Medication medication;
    private RepoInterface repoInterface;

    private AddMedicationPresenter(Context context, RepoInterface repoInterface){
        this.context = context;
        this.repoInterface = repoInterface;
    }

    public static AddMedicationPresenter getInstance(Context context, RepoInterface repoInterfaceI){
        if(addMedicationPresenter ==null){
            addMedicationPresenter = new AddMedicationPresenter(context,repoInterfaceI);
        }
        return addMedicationPresenter;

    }

    @Override
    public LiveData<List<MedicationList>> getAllDrugs() {
        return repoInterface.getAllDrugs();
    }

    @Override
    public LiveData<MedicationList> getDrugs() {
        String date = "16/3/2022";
        date = formatCalenderDate(date);
        return repoInterface.getDrugs(date);
    }

    public int getAnswer(Medication medication){
        this.medication = medication;
        int answer =0 ;

        if(medication.getEveryDayOr().equals("Yes")){
            answer = 1;
        }
        else if(medication.getEveryDayOr().equals("No")){
            answer = 2;
        }
        return  answer;
    }
    public  void calListHour(Medication medication){
        this.medication = medication;
        String timeDay = medication.getTimesInday();
        List<String>  hours = new ArrayList<>();
        switch (timeDay){
            case "Once day":
                hours.add(medication.getFirstTimeDose());
                medication.setHours(hours);
                break;
            case "Twice day":
                addMedicationPresenter.setListHour(2,12);
                break;
            case "3 times in day":
                addMedicationPresenter.setListHour(3,8);
                break;
            case "4 times in day":
                addMedicationPresenter.setListHour(4,6);
                break;
        }
    }
    private void setListHour(int times,int eachHour){
        List<String> hours = new ArrayList<>();
        medication = Medication.getInstance();
        String time = medication.getFirstTimeDose();
        hours.add(time);

        for(int i =1 ; i<times ; i++){
            time = hours.get(i-1);
            String[] separated = time.split(":");
            String hour = separated[0];
            String minutes = separated[1];

           String h = String.valueOf(Integer.parseInt(hour)+eachHour);
           if(Integer.parseInt(h)>=24){
               h=String.valueOf(Integer.parseInt(h)-24);
           }
            hours.add(h+":"+minutes);
        }
        medication.setHours(hours);
    }
    public  void calListDay(Medication medication){
        this.medication = medication;
        String duration = medication.getDurationDrug();
        switch (duration){
            case "30 days":
                addMedicationPresenter.setDays(30);
                break;
            case "1 week":
                addMedicationPresenter.setDays(7);
                break;
            case "10 days":
                addMedicationPresenter.setDays(10);
                break;
            case "5 days":
                addMedicationPresenter.setDays(5);
                break;
        }
    }
    private void setDays(int duration) {
        medication = Medication.getInstance();
        String firstDate = medication.getFirstDateDose();
        days(firstDate, duration);
    }
    public void days(String firstDate,int duration){
        List<MedicationDose> medDose = new ArrayList<>();
        String date =firstDate;
        date = formatCalenderDate(date);

        // list for name , hour
            for(int i =0 ;i<medication.getHours().size();i++){
                MedicationDose med = new MedicationDose();
                med.setName(medication.getName());
                med.setHour(medication.getHours().get(i));
                medDose.add(med);
            }

            // list for date , list (name,hour)
            for(int i =0;i<duration;i++){
                repoInterface.addDrug(new MedicationList(date,medDose));
               date = incrementCalenderDate(date);
            }
    }

    public String formatCalenderDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            date = sdf.format(c.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public String incrementCalenderDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            c.add(Calendar.DAY_OF_MONTH, 1);
            date = sdf.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
