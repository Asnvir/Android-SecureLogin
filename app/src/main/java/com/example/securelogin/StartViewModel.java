package com.example.securelogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StartViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isLocationSelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isBatterySelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isTimeSelectedLiveData = new MutableLiveData<>();


    // Constructor
    public StartViewModel() {

    }

    public void setIsLocationSelected(boolean selected) {
        isLocationSelectedLiveData.setValue(selected);
    }

    public void setIsBatterySelected(boolean selected) {
        isBatterySelectedLiveData.setValue(selected);
    }

    public void setIsTimeSelected(boolean selected) {
        isTimeSelectedLiveData.setValue(selected);
    }


    public LiveData<Boolean> getIsBatterySelectedLiveData() {
        return isBatterySelectedLiveData;
    }

    public LiveData<Boolean> getIsTimeSelectedLiveData() {
        return isTimeSelectedLiveData;
    }

    public LiveData<Boolean> getIsLocationSelectedLiveData() {
        return isLocationSelectedLiveData;
    }


    public void login() {
        boolean isLocationSelected = isLocationSelectedLiveData.getValue() != null ? isLocationSelectedLiveData.getValue() : false;
        boolean isBatterySelected = isBatterySelectedLiveData.getValue() != null ? isBatterySelectedLiveData.getValue() : false;
        boolean isTimeSelected = isTimeSelectedLiveData.getValue() != null ? isTimeSelectedLiveData.getValue() : false;

        String combination =
                (isLocationSelected ? "1" : "0") + "|" +
                (isBatterySelected ? "1" : "0") + "|" +
                (isTimeSelected ? "1" : "0");

        CombinationStrategy strategy = CombinationStrategyFactory.createCombinationStrategy(combination);

        strategy.performActions();
    }


}
