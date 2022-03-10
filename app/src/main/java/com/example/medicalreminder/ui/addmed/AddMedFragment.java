package com.example.medicalreminder.ui.addmed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.fragment.NavHostFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medicalreminder.R;


public class AddMedFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddMedViewModel addMedViewModel =
                new ViewModelProvider(this).get(AddMedViewModel.class);

        View view =inflater.inflate(R.layout.fragment_add_medication, container, false);
        NavHostFragment navHostFragment= (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragment_container_add_med);
        NavController navController= navHostFragment.getNavController();
        NavGraph navGraph= navController.getNavInflater().inflate(R.navigation.add_med_navigation);
        navGraph.setStartDestination(R.id.nameDrugQue);
        navController.setGraph(navGraph);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}