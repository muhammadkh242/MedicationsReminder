package com.example.medicalreminder.addMedication.presenter;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
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
    public LiveData<List<MedicationList>> getInfoMed() {

        return repoInterface.getDrugs("14/3/2022");
    }

    @Override
    public void addInfoMed(Medication medication) {
        this.medication = medication;
        Drug drug = new Drug();
        drug.setName(medication.getName());
        drug.setForm(medication.getForm());
        drug.setHours(medication.getHours());
       drug.setDays(medication.getDays());
        repoInterface.getDrugs("14/3/2022");
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
                hours.add(medication.getFirstDateDose());
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
            Log.i("TAG", "setListHour: "+h);
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
    private void setDays(int duration){
        medication = Medication.getInstance();
        String firstDate = medication.getFirstDateDose();
        days(firstDate,duration);
          }
    public void days(String firstDate,int duration){

        List<String> days = new ArrayList<>();
        List<MedicationDose> medDose = new ArrayList<>();
        String dt =firstDate;
        days.add(firstDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        try {
            // list for name , hour
            for(int i =0 ;i<medication.getHours().size();i++){
                MedicationDose med = new MedicationDose();
                med.setName(medication.getName());
                med.setHour(medication.getHours().get(i));
                medDose.add(med);
            }

            c.setTime(sdf.parse(dt));
            dt = sdf.format(c.getTime());
            //repoInterface.addDrug(new MedicationList(dt,medDose));
            // list for date , list (name,hour)
            for(int i =0;i<duration;i++){
                //if(repoInterface.connection()){
                repoInterface.sendDrug(new MedicationList(dt,medDose));
                //repoInterface.getDurgs(new MedicationList(dt,medDose));
                //Log.i("TAG", "days: "+m.getDate());
                //}
                //else{
                    //repoInterface.addDrug(new MedicationList(dt,medDose));

                //}
                c.setTime(sdf.parse(dt));
                c.add(Calendar.DAY_OF_MONTH, 1);
                dt = sdf.format(c.getTime());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
