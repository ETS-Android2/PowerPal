package com.example.powerpal.ui.appliances;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppliancesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AppliancesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is appliances fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}