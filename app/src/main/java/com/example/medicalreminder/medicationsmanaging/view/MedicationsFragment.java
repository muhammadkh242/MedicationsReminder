package com.example.medicalreminder.medicationsmanaging.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicalreminder.HomeActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.addMedication.view.AddMedFragment;
import com.example.medicalreminder.displaymedication.view.DisplayDrugDetails;
import com.example.medicalreminder.local.db.ConcreteLocalSource;
import com.example.medicalreminder.medicationsmanaging.presenter.MedicationsPresenter;
import com.example.medicalreminder.medicationsmanaging.presenter.MedicationsPresenterInterface;
import com.example.medicalreminder.model.Med;
import com.example.medicalreminder.model.UserMed;
import com.example.medicalreminder.model.addmedication.Drug;
import com.example.medicalreminder.model.addmedication.MedicationList;
import com.example.medicalreminder.model.addmedication.Repo;
import com.example.medicalreminder.model.medicationsmanaging.MedicationsRepo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedicationsFragment extends Fragment implements OnMedClickListener, MedicationsViewInterface{

    RecyclerView activeRecycler;

    List<UserMed> medList = new ArrayList<>();
    RecyclerAdapter activeAdapter;

    Button addBtn;
    LinearLayoutManager layoutManager;

    MedicationsPresenterInterface presenter;

    UserMed userMed;

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

        /*if(Repo.getInstance(getContext(),ConcreteLocalSource.getInstance(getContext())).connection()){
            getMeds();

        }
        else{
            getAllMeds();
        }*/
        getAllMeds();
        return view;
    }


//
//    public void getMeds(){
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference("meds");
//        Query query = db.child(FirebaseAuth.getInstance().getUid()).orderByKey();
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    UserMed userMed = dataSnapshot.getValue(UserMed.class);
//                    Log.i("TAG", "form: " + userMed.getForm());
//                    Log.i("TAG", "name: " + userMed.getName());
//                    Log.i("TAG", "___________________________");
//
//                    medList.add(userMed);
////                    Log.i("TAG", "onDataChange: " + medList.size());
//
//                }
//                Log.i("TAG", "onDataChange: list size : " + medList.size());
//                activeAdapter.setData(medList);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }

    /*@Override
    public void onClick(UserMed userMed) {
        Intent intent = new Intent(getContext(), DisplayDrugDetails.class);
        intent.putExtra("userMed", (Serializable) userMed);
        startActivity(intent);
    }*/

    /*@Override
    public void showMeds(MutableLiveData<List<UserMed>> meds) {
//        Log.i("TAG", "showMeds: " + meds.size());
        meds.observe((LifecycleOwner) getContext(), new Observer<List<UserMed>>() {
            @Override
            public void onChanged(List<UserMed> userMeds) {
                activeAdapter.setData(userMeds);
                activeRecycler.setAdapter(activeAdapter);
            }
        });
    }*/

    @Override
    public void showMeds(MutableLiveData<List<UserMed>> meds) {

    }

    @Override
    public void getMeds() {
        presenter.getMeds();
    }

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
        intent.putExtra("userMed", (Serializable) drug);
        startActivity(intent);
    }
}