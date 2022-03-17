package com.example.medicalreminder.medicationsmanaging.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.R;
import com.example.medicalreminder.model.Med;

import java.util.ArrayList;
import java.util.List;

public class MedicationsFragment extends Fragment {
    RecyclerView activeRecycler;

    List<Med> medList = new ArrayList<>();
    RecyclerAdapter activeAdapter;


    LinearLayoutManager layoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.medications_fragment, container, false);
        activeRecycler = view.findViewById(R.id.recycler_one);


        activeRecycler.setHasFixedSize(true);


        activeAdapter = new RecyclerAdapter(getContext());

        //bind layout manager to your context that contains the fragment
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        //bind recyclerview to layout manager
        activeRecycler.setLayoutManager(layoutManager);

        //bind recyclerview to adapter
        activeRecycler.setAdapter(activeAdapter);
        activeAdapter.setData(initData());


        return view;
    }


    //static data to test
    private List<Med> initData(){
        medList.add(new Med("one",30,"3 pills left", R.drawable.one));
        medList.add(new Med("two",40,"4 pills left", R.drawable.two));
        medList.add(new Med("three",50,"5 pills left", R.drawable.three));
        medList.add(new Med("four",60,"6 pills left", R.drawable.four));
        medList.add(new Med("five",70,"7 pills left", R.drawable.five));
        medList.add(new Med("six",80,"8 pills left", R.drawable.six));
        return medList;
    }
}