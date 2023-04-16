package com.example.securelogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StartViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isGpsSelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isBatterySelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isTimeSelectedLiveData = new MutableLiveData<>();
    private final StartModel startModel;



    public StartViewModel() {
        startModel = new StartModel();
    }

    public void setIsLocationSelected(boolean selected) {
        isGpsSelectedLiveData.setValue(selected);
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

    public LiveData<Boolean> getIsGpsSelectedLiveData() {
        return isGpsSelectedLiveData;
    }


    public void login() {
        boolean isGpsSelected = isGpsSelectedLiveData.getValue() != null ? isGpsSelectedLiveData.getValue() : false;
        boolean isBatterySelected = isBatterySelectedLiveData.getValue() != null ? isBatterySelectedLiveData.getValue() : false;
        boolean isTimeSelected = isTimeSelectedLiveData.getValue() != null ? isTimeSelectedLiveData.getValue() : false;


        startModel.login(isGpsSelected,isBatterySelected,isTimeSelected);

    }


    public void setLocationCoordinates(double[] coordinates) {
        startModel.setUpCoordinates(coordinates);
    }

    public void setBatteryPercentage(int batteryPercentage) {
        startModel.setUpBatteryPercentage(batteryPercentage);
    }

    public void setTime(String currentTime) {
        startModel.setUpCurrentTime(currentTime);
    }
}
