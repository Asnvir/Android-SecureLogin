package com.example.securelogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.GpsUtil;

public class StartViewModel extends ViewModel {

    public interface LoginCallback {
        void onLogin(Boolean isSuccess);

        void isGpsSelected(Boolean isGpsSelected);

        void isTimeSelected(Boolean isTimeSelected);

        void isBatterySelected(Boolean isBatterySelected);
    }


    private final MutableLiveData<Boolean> isGpsSelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isBatterySelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isTimeSelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoginLiveData = new MutableLiveData<>();
    private final StartModel startModel;


    public StartViewModel(GpsUtil gpsUtil, BatteryUtil batteryUtil) {
        startModel = new StartModel(gpsUtil, batteryUtil);
        LoginCallback mLoginCallback = new LoginCallback() {
            @Override
            public void onLogin(Boolean isSuccess) {
                isLoginLiveData.setValue(isSuccess);
            }

            @Override
            public void isGpsSelected(Boolean isGpsSelected) {
                isGpsSelectedLiveData.setValue(isGpsSelected);
            }

            @Override
            public void isTimeSelected(Boolean isTimeSelected) {
                isTimeSelectedLiveData.setValue(isTimeSelected);
            }

            @Override
            public void isBatterySelected(Boolean isBatterySelected) {
                isBatterySelectedLiveData.setValue(isBatterySelected);
            }
        };
        startModel.setStartViewModelCallback(mLoginCallback);
    }

    public void setIsLocationSelected(boolean selected) {
        startModel.setIsLocationSelected(selected);
    }


    public void setIsBatterySelected(boolean selected) {
        startModel.setIsBatterySelected(selected);
    }

    public void setIsTimeSelected(boolean selected) {
        startModel.setTimeSelected(selected);
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

    public LiveData<Boolean> getIsLoginLiveData() {
        return isLoginLiveData;
    }


    public void login() {
        boolean isGpsSelected = isGpsSelectedLiveData.getValue() != null ? isGpsSelectedLiveData.getValue() : false;
        boolean isBatterySelected = isBatterySelectedLiveData.getValue() != null ? isBatterySelectedLiveData.getValue() : false;
        boolean isTimeSelected = isTimeSelectedLiveData.getValue() != null ? isTimeSelectedLiveData.getValue() : false;


        startModel.login(isGpsSelected, isBatterySelected, isTimeSelected);

    }

}
