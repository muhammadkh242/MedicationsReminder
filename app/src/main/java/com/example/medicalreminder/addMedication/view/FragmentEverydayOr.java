package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.calculation.CalculationMedication;
import com.example.medicalreminder.databinding.EverydayOrQuestionScreenBinding;
import com.example.medicalreminder.databinding.NameDrugQuestionScreenBinding;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.reposatiry.Repo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentEverydayOr  extends Fragment  implements OnAddMedClickListner {

    AddMedicationPresenterInterface addMedPI;
    Medication medication;
    AddMedicationAdapter addMedicationAdapter;
    EverydayOrQuestionScreenBinding binding;
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

        binding = EverydayOrQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        getInti();

        medication = (Medication) getArguments().getSerializable("object");

        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(addMedicationAdapter);

        list.add("Yes");
        list.add("No");
        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                Bundle bundle = new Bundle();

                int answer = CalculationMedication.getAnswer();
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

        return root;
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
