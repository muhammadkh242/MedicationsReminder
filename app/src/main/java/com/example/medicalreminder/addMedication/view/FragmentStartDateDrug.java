package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.addmedication.Medication;

import java.io.Serializable;

public class FragmentStartDateDrug  extends Fragment {

    Medication medication;
    DatePicker datePicker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.startdate_drug_question_screen, container, false);
        medication = Medication.getInstance();
        datePicker =  view.findViewById(R.id.date);
        medication = (Medication) getArguments().getSerializable("object");

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = datePicker.getDayOfMonth();
                int month =   (datePicker.getMonth() + 1);
                int year =  datePicker.getYear();
                medication.setFirstDateDose(day+"/"+month+"/"+year);

                NavController navController= Navigation.findNavController(view);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.startTimeAct,bundle);

            }
        });
        return view;

    }


//
//    Calendar calendar = Calendar.getInstance();
//                System.out.println();
//                System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//                System.out.println();
//    Medication medication = new Medication();
//    MedicationHours medicationHours = new MedicationHours();
//    Vector<String> list = new Vector<>();
//    HashMap<String,Medication> med = new HashMap();
//    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//                list.add(currentTime);
//                medicationHours.setListHours(list);
//                medication.setName("panadol");
//                medication.setMedHours(medicationHours);
//               for(int i =0;i<14;i++){
//        med.put(String.valueOf(day),medication);
//        Log.i("TAG", "onClick: "+med.get(String.valueOf(day)).getMedHours().getListHours());
//        day++;
//
//    }
//                for (HashMap.Entry<String, Medication> entry : med.entrySet()) {
//        System.out.println(entry.getKey() + " = " + entry.getValue());
//    }


}
