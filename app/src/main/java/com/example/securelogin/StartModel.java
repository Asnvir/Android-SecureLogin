package com.example.securelogin;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.ConcreteCombinationStrategyFactory;
import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.GpsUtil;
import com.example.securelogin.util.TimeUtil;

public class StartModel {

    public interface GpsPermissionCallback {
        void onPermissionStatus(Boolean status);

    }

    public interface GpsCoordinatesCallback {
        void onGPSCoordinatesAvailable(double[] coordinates);

        void onFailure(Exception e);
    }

    private int batteryPercentage;
    private String currentTime;
    private final GpsUtil mGpsUtil;
    private final BatteryUtil mBatteryUtil;
    private StartViewModel.LoginCallback mStartViewModel;

    public StartModel(GpsUtil gpsUtil, BatteryUtil batteryUtil) {
        mBatteryUtil = batteryUtil;
        mGpsUtil = gpsUtil;
    }


    public void login(boolean gpsIsSelected, boolean batteryIsSelected, boolean timeIsSelected) {
        String combination =
                (gpsIsSelected ? "1" : "0") + "|" +
                        (batteryIsSelected ? "1" : "0") + "|" +
                        (timeIsSelected ? "1" : "0");

        if (batteryIsSelected) {
            batteryPercentage = mBatteryUtil.getBatteryPercentage();
        }

        if (timeIsSelected) {
            currentTime = TimeUtil.getCurrentTime();
        }

        if (gpsIsSelected) {
            mGpsUtil.getCoordinates(new GpsCoordinatesCallback() {

                @Override
                public void onGPSCoordinatesAvailable(double[] coordinates) {
                    executeLogin(combination, coordinates);
                }

                @Override
                public void onFailure(Exception e) {
                    mStartViewModel.setExceptionMessage(e.getMessage());
                }
            });
        } else {
            executeLogin(combination, new double[]{0, 0});
        }
    }

    private void executeLogin(String combination, double[] coordinates) {
        ConcreteCombinationStrategyFactory combinationStrategyFactory = new ConcreteCombinationStrategyFactory();
        CombinationStrategy combinationStrategy = combinationStrategyFactory.createCombinationStrategy(combination, coordinates, currentTime, batteryPercentage);
        mStartViewModel.onLogin(combinationStrategy.isConditionValid());
    }

    public void setStartViewModelCallback(StartViewModel.LoginCallback startViewModelCallback) {
        this.mStartViewModel = startViewModelCallback;
    }

    public void setIsLocationSelected(boolean selected) {
        if (selected) {
            mGpsUtil.checkPermission(status -> mStartViewModel.isGpsSelected(status));
        } else {
            mStartViewModel.isGpsSelected(false);
        }
    }

    public void setIsBatterySelected(boolean selected) {
        mStartViewModel.isBatterySelected(selected);
    }

    public void setTimeSelected(boolean selected) {
        mStartViewModel.isTimeSelected(selected);
    }
}

