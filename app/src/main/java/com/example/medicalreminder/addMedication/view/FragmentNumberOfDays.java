package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.Repo;

import java.util.ArrayList;
import java.util.List;

public class FragmentNumberOfDays  extends Fragment implements OnAddMedClickListner {

    RecyclerView recyclerView;
    AddMedicationAdapter addMedicationAdapter;
    AddMedicationPresenterInterface addMedPI;
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
        View view= inflater.inflate(R.layout.number_of_days_question_screen, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addMedicationAdapter);

        getInti();
        list.add("Once week");
        list.add("Twice week");
        list.add("3 times in week");
        list.add("4 times in week");

        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavDirections navDirections = FragmentNumberOfDaysDirections.startdateDrugAct();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(navDirections);
            }
        });
        return view;

    }

    public void getInti(){
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        addMedPI = AddMedicationPresenter.getInstance(getContext(), Repo.getInstance(getContext(),
                ConcreteLocalSource.getInstance(getContext())));
        layoutManager = new LinearLayoutManager(getContext());
        medication = Medication.getInstance();

    }

    @Override
    public void onClick(String txt) {
        medication.setTimesInWeeks(txt);
    }
}
