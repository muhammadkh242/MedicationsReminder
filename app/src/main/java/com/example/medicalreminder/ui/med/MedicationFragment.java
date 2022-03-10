package com.example.medicalreminder.ui.med;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.medicationsmanaging.view.RecyclerAdapter;
import com.example.medicalreminder.model.Med;

import java.util.ArrayList;
import java.util.List;

public class MedicationFragment extends Fragment {

//    private FragmentMedicationBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        MedicationViewModel medicationViewModel =
//                new ViewModelProvider(this).get(MedicationViewModel.class);
//
//        binding = FragmentMedicationBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textNotifications;
//        medicationViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
RecyclerView activeRecycler;
    RecyclerView inactiveRecycler;
    List<Med> medList = new ArrayList<>();
    RecyclerAdapter activeAdapter;
    RecyclerAdapter inactiveAdapter;

    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManagerTwo;


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
        inactiveRecycler = view.findViewById(R.id.recycler_two);

        activeRecycler.setHasFixedSize(true);
        inactiveRecycler.setHasFixedSize(true);

        activeAdapter = new RecyclerAdapter(getContext());
        inactiveAdapter = new RecyclerAdapter(getContext());

        //bind layout manager to your context that contains the fragment
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManagerTwo = new LinearLayoutManager(getContext());
        layoutManagerTwo.setOrientation(LinearLayoutManager.VERTICAL);

        //bind recyclerview to layout manager
        activeRecycler.setLayoutManager(layoutManager);
        inactiveRecycler.setLayoutManager(layoutManagerTwo);

        //bind recyclerview to adapter
        activeRecycler.setAdapter(activeAdapter);
        activeAdapter.setData(initData());

        inactiveRecycler.setAdapter(inactiveAdapter);
        inactiveAdapter.setData(initData());

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