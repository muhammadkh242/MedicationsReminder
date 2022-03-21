package com.example.medicalreminder.addMedication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenter;
import com.example.medicalreminder.addMedication.presenter.AddMedicationPresenterInterface;
import com.example.medicalreminder.addMedication.view.adapter.AddMedicationAdapter;
import com.example.medicalreminder.addMedication.view.adapter.OnAddMedClickListner;
import com.example.medicalreminder.databinding.NumberOfDaysQuestionScreenBinding;
import com.example.medicalreminder.local.ConcreteLocalSource;
import com.example.medicalreminder.model.addmedication.Medication;
import com.example.medicalreminder.model.addmedication.reposatiry.RepoAdd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentTimesInWeek extends Fragment implements OnAddMedClickListner {

    AddMedicationAdapter addMedicationAdapter;
    AddMedicationPresenterInterface addMedPI;
    List<String> list;
    LinearLayoutManager layoutManager;
    NumberOfDaysQuestionScreenBinding binding;
    Medication medication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = NumberOfDaysQuestionScreenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        getInti();

        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(addMedicationAdapter);

        list.add("Once week");
        list.add("Twice week");
        list.add("3 times in week");
        list.add("4 times in week");

        addMedicationAdapter.setList(list);
        addMedicationAdapter.notifyDataSetChanged();

       binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController= Navigation.findNavController(root);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object", (Serializable) medication);
                navController.navigate(R.id.startdateDrugAct,bundle);
            }
        });
        return root;
    }

    public void getInti(){
        list = new ArrayList<>();
        addMedicationAdapter = new AddMedicationAdapter(getContext(),this);
        addMedPI = AddMedicationPresenter.getInstance(getContext(), RepoAdd.getInstance(getContext(),
                ConcreteLocalSource.getInstance(getContext())));
        layoutManager = new LinearLayoutManager(getContext());
        medication = Medication.getInstance();

    }

    @Override
    public void onClick(String txt) {
        medication.setTimesInWeeks(txt);
    }
}
