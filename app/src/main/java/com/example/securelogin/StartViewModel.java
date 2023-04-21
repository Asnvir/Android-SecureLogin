package com.example.securelogin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.ConditionResult;
import com.example.securelogin.util.GpsUtil;
import com.example.securelogin.util.SingleLiveData;

public class StartViewModel extends ViewModel {

    public interface LoginCallback {
        void onLogin(ConditionResult ConditionResult);
        void isGpsSelected(Boolean isGpsSelected);
        void isTimeSelected(Boolean isTimeSelected);
        void isBatterySelected(Boolean isBatterySelected);
        void setExceptionMessage(String message);
    }


    private final MutableLiveData<Boolean> isGpsSelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isBatterySelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isTimeSelectedLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessageLiveData = new MutableLiveData<>();
    private final SingleLiveData<ConditionResult> conditionResultMutableLiveData = new SingleLiveData<>();
    private final StartModel startModel;


    public StartViewModel(GpsUtil gpsUtil, BatteryUtil batteryUtil) {
        startModel = new StartModel(gpsUtil, batteryUtil);
        LoginCallback mLoginCallback = new LoginCallback() {
            @Override
            public void onLogin(ConditionResult conditionResult) {
                conditionResultMutableLiveData.setValue(conditionResult);
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

            @Override
            public void setExceptionMessage(String message) {
                errorMessageLiveData.setValue(message);
            }
        };
        startModel.setStartViewModelCallback(mLoginCallback);
    }


    public void login() {
        boolean isGpsSelected = isGpsSelectedLiveData.getValue() != null ? isGpsSelectedLiveData.getValue() : false;
        boolean isBatterySelected = isBatterySelectedLiveData.getValue() != null ? isBatterySelectedLiveData.getValue() : false;
        boolean isTimeSelected = isTimeSelectedLiveData.getValue() != null ? isTimeSelectedLiveData.getValue() : false;
        startModel.login(isGpsSelected, isBatterySelected, isTimeSelected);
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

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }

    public LiveData<ConditionResult> getConditionResultMutableLiveData() {return conditionResultMutableLiveData;}

}
