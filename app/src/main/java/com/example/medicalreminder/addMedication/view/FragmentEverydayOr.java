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

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.local.db.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.Repo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentEverydayOr  extends Fragment  implements OnAddMedClickListner {

    AddMedicationPresenterInterface addMedPI;
    Medication medication;
    RecyclerView recyclerView;
    AddMedicationAdapter addMedicationAdapter;
    List<String> list;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.everyday_or_question_screen, container, false);
        getInti();

        medication = (Medication) getArguments().getSerializable("object");
        Log.i("TAG", "onCreateView: "+medication.getName()+ medication.getForm());


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addMedicationAdapter);

        list.add("Yes");
        list.add("No");
        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                Bundle bundle = new Bundle();

                int answer = addMedPI.getAnswer(medication);
                if(answer ==1){
                    bundle.putSerializable("object", (Serializable) medication);
                    navController.navigate(R.id.numberTakeDayAct,bundle);

                }
                else if(answer ==2) {
                    bundle.putSerializable("object", (Serializable) medication);
                    navController.navigate(R.id.numberDaysAct,bundle);
                }

            }
        });

        return view;

    }

    public void getInti(){
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        addMedPI = AddMedicationPresenter.getInstance(getContext(), Repo.getInstance(getContext(), ConcreteLocalSource.getInstance(getContext())));
        layoutManager = new LinearLayoutManager(getContext());
        medication = Medication.getInstance();

    }
    @Override
    public void onClick(String txt) {
        medication.setEveryDayOr(txt);
    }

}
