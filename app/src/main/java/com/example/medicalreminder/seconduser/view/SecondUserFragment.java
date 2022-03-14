package com.example.medicalreminder.seconduser.view;

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

public class SecondUserFragment extends Fragment {

    RecyclerView secondRecycler;
    List<Med> medList = new ArrayList<>();
    SecondAdapter adapter;
    LinearLayoutManager layoutManager;


    public SecondUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_second_user, container, false);
        secondRecycler = view.findViewById(R.id.recycler_second);
        secondRecycler.setHasFixedSize(true);

        adapter = new SecondAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        secondRecycler.setLayoutManager(layoutManager);

        secondRecycler.setAdapter(adapter);

        adapter.setData(initData());

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