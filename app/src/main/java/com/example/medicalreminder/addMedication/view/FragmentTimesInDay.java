package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.R;
import com.example.medicalreminder.model.addmedication.Medication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentTimesInDay extends Fragment  implements OnAddMedClickListner {
    RecyclerView recyclerView;
    AddMedicationAdapter addMedicationAdapter;
    List<String> list;
    LinearLayoutManager layoutManager;
    Medication medication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.number_of_taked_day_question_screen, container, false);
        getInti();

        medication = (Medication) getArguments().getSerializable("object");
        Log.i("TAG", "onCreateView: "+medication.getName()+ medication.getForm()+medication.getEveryDayOr());


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addMedicationAdapter);

        list.add("Once day");
        list.add("Twice day");
        list.add("3 times in day");
        list.add("4 times in day");

        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController= Navigation.findNavController(view);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.startdateDrugAct,bundle);
            }
        });
        return view;

    }


    @Override
    public void onClick(String txt) {
        medication.setTimesInday(txt);
    }

    public void getInti(){
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        layoutManager = new LinearLayoutManager(getContext());
        medication = Medication.getInstance();

    }
}
