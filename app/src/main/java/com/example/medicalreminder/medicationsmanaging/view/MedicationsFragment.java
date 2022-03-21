package com.example.medicalreminder.medicationsmanaging.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.displaymedication.view.DisplayDrugDetails;
import com.example.medicalreminder.local.dbmedication.ConcreteLocalSource;
import com.example.medicalreminder.medicationsmanaging.presenter.MedicationsPresenter;
import com.example.medicalreminder.medicationsmanaging.presenter.MedicationsPresenterInterface;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.reposatiry.Repo;
import com.example.medicalreminder.model.medicationsmanaging.MedicationsRepo;

import java.io.Serializable;
import java.util.List;

public class MedicationsFragment extends Fragment implements OnMedClickListener, MedicationsViewInterface{

    RecyclerView activeRecycler;

    RecyclerAdapter activeAdapter;

    Button addBtn;
    LinearLayoutManager layoutManager;

    MedicationsPresenterInterface presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_medication, container, false);

        addBtn = view.findViewById(R.id.addMedBtn);
        activeRecycler = view.findViewById(R.id.recycler_one);


        activeRecycler.setHasFixedSize(true);


        activeAdapter = new RecyclerAdapter(getContext(), this);

        //bind layout manager to your context that contains the fragment
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        //bind recyclerview to layout manager
        activeRecycler.setLayoutManager(layoutManager);
        //bind recyclerview to adapter
        presenter = new MedicationsPresenter
                (MedicationsRepo.getMedicationsRepo(getContext(),
                        ConcreteLocalSource.getInstance(getContext())), this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to add med
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);

            }
        });

        if(Repo.getInstance(getContext(),ConcreteLocalSource.getInstance(getContext())).connection()){
            getMeds();

        }
        else{
            getAllMeds();
        }

        return view;
    }


    //FROM REMOTE
    @Override
    public void showMeds(MutableLiveData<List<Drug>> meds) {
        meds.observe((LifecycleOwner) getContext(), new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {
                activeAdapter.setData(drugs);
                activeRecycler.setAdapter(activeAdapter);
            }
        });

    }

    @Override
    public void getMeds() {
        presenter.getMeds();
    }

    //FROM ROOM
    @Override
    public void showAllMeds(LiveData<List<Drug>> list) {
        list.observe((LifecycleOwner) getContext(), new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugs) {
                activeAdapter.setData(drugs);
                activeRecycler.setAdapter(activeAdapter);
            }
        });
    }

    @Override
    public void getAllMeds() {
        Log.i("TAG", "getAllMeds: ");
        presenter.getAllMeds();
    }

    @Override
    public void onClick(Drug drug) {
        Intent intent = new Intent(getContext(), DisplayDrugDetails.class);
        intent.putExtra("drug", (Serializable) drug);
        startActivity(intent);
    }
}