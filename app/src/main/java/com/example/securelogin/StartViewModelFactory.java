package com.example.securelogin;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.GpsUtil;

public class StartViewModelFactory implements ViewModelProvider.Factory {

    private final GpsUtil mGpsUtil;
    private final BatteryUtil mBatteryUtil;

    public StartViewModelFactory(GpsUtil gpsUtil, BatteryUtil batteryUtil) {
        mGpsUtil = gpsUtil;
        mBatteryUtil = batteryUtil;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StartViewModel.class)) {
            return (T) new StartViewModel(mGpsUtil, mBatteryUtil);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
