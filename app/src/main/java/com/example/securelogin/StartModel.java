package com.example.securelogin;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.ConcreteCombinationStrategyFactory;

public class StartModel {


    private double[] coordinates;
    private int batteryPercentage;
    private String currentTime;
    private StartViewModel.LoginCallback loginCallback;

    public StartModel (){

    }


    public void login(boolean isGpsSelected, boolean isBatterySelected, boolean isTimeSelected) {
        String combination =
                        (isGpsSelected ? "1" : "0") + "|" +
                        (isBatterySelected ? "1" : "0") + "|" +
                        (isTimeSelected ? "1" : "0");


        ConcreteCombinationStrategyFactory combinationStrategyFactory = new ConcreteCombinationStrategyFactory();
        CombinationStrategy combinationStrategy = combinationStrategyFactory.createCombinationStrategy(combination,coordinates,currentTime,batteryPercentage);

        boolean isConditionValid = combinationStrategy.isConditionValid();

        if(loginCallback != null){
            loginCallback.onLogin(isConditionValid);
        }

    }

    public void setUpCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setUpBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public void setUpCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setLoginCallback(StartViewModel.LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
}

