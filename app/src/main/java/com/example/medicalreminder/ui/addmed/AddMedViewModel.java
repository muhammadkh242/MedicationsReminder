package com.example.medicalreminder.ui.addmed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddMedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddMedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}