package com.example.securelogin;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.ConcreteCombinationStrategyFactory;
import com.example.securelogin.util.BatteryUtil;
import com.example.securelogin.util.GpsUtil;
import com.example.securelogin.util.TimeUtil;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StartModel {

    public interface GpsPermissionCallback {
        void onPermissionStatus(Boolean status);

        void onLocationReceived(double[] coordinates);
    }


    private double[] coordinates;
    private int batteryPercentage;
    private String currentTime;
    private final GpsUtil mGpsUtil;
    private final BatteryUtil mBatteryUtil;
    private StartViewModel.LoginCallback mStartViewModel;
    private GpsPermissionCallback mGpsPermissionCallback = new GpsPermissionCallback() {
        @Override
        public void onPermissionStatus(Boolean status) {
            mStartViewModel.isGpsSelected(status);
        }

        @Override
        public void onLocationReceived(double[] coordinates) {
            setUpCoordinates(coordinates);
        }
    };


    public StartModel(GpsUtil gpsUtil, BatteryUtil batteryUtil) {
        mBatteryUtil = batteryUtil;
        mGpsUtil = gpsUtil;

        mGpsUtil.setGpsPermissionCallback(mGpsPermissionCallback);
    }


//    public void login(boolean isGpsSelected, boolean isBatterySelected, boolean isTimeSelected) {
//        String combination =
//                        (isGpsSelected ? "1" : "0") + "|" +
//                        (isBatterySelected ? "1" : "0") + "|" +
//                        (isTimeSelected ? "1" : "0");
//
//
//        if(isGpsSelected && mGpsUtil != null){
//            mGpsUtil.getCoordinates();
//        }
//
//        if(isBatterySelected && mBatteryUtil != null){
//            batteryPercentage = mBatteryUtil.getBatteryPercentage();
//        }
//
//        if(isTimeSelected){
//            currentTime = TimeUtil.getCurrentTime();
//        }
//
//
//        ConcreteCombinationStrategyFactory combinationStrategyFactory = new ConcreteCombinationStrategyFactory();
//        CombinationStrategy combinationStrategy = combinationStrategyFactory.createCombinationStrategy(combination,coordinates,currentTime,batteryPercentage);
//
//        boolean isConditionValid = combinationStrategy.isConditionValid();
//
//        if(mStartViewModel != null){
//            mStartViewModel.onLogin(isConditionValid);
//        }
//    }

    private Disposable subscription;

    public void login(boolean gpsIsSelected, boolean batteryIsSelected, boolean timeIsSelected) {
        String combination =
                (gpsIsSelected ? "1" : "0") + "|" +
                (batteryIsSelected ? "1" : "0") + "|" +
                (timeIsSelected ? "1" : "0");

        if (gpsIsSelected && mGpsUtil != null) {
            subscription = Observable.fromCallable(() -> {
                        double[] coordinates = mGpsUtil.getCoordinates();
                        return checkCombinationStrategyValidity(combination, coordinates);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isConditionValid -> {
                        if (mStartViewModel != null) {
                            mStartViewModel.onLogin(isConditionValid);
                        }
                        unsubscribe();
                    }, throwable -> unsubscribe());
        } else {
            double[] coordinates = new double[]{0, 0};
            boolean isConditionValid = checkCombinationStrategyValidity(combination, coordinates);
            if (mStartViewModel != null) {
                mStartViewModel.onLogin(isConditionValid);
            }
        }
    }

    public void unsubscribe() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }


    private boolean checkCombinationStrategyValidity(String combination, double[] coordinates) {
        ConcreteCombinationStrategyFactory combinationStrategyFactory = new ConcreteCombinationStrategyFactory();
        CombinationStrategy combinationStrategy = combinationStrategyFactory.createCombinationStrategy(combination, coordinates, currentTime, batteryPercentage);
        return combinationStrategy.isConditionValid();
    }



    public void setStartViewModelCallback(StartViewModel.LoginCallback startViewModelCallback) {
        this.mStartViewModel = startViewModelCallback;
    }

    private void setUpCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setIsLocationSelected(boolean selected) {
        if (selected) {
            mGpsUtil.checkPermission();
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

