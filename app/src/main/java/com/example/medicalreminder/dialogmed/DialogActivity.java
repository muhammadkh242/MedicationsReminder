package com.example.medicalreminder.dialogmed;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.ActivityHomeBinding;
import com.example.medicalreminder.databinding.ActivityInvitationBinding;

public class DialogActivity extends AppCompatActivity {

    private ActivityInvitationBinding binding;
    private FragmentManager manager;
    private DialogFragment dialogFragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInvitationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dialogFragment = new DialogFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.add(R.id.fragmentContainerInvitation, dialogFragment, "fragmentTxt");
        transaction.commit();

    }
}
