package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class TimeAndBatteryStrategy implements CombinationStrategy {
    private final String currentTime;
    private final int batteryPercentage;

    public TimeAndBatteryStrategy(String currentTime, int batteryPercentage) {

        this.currentTime = currentTime;
        this.batteryPercentage =batteryPercentage;
    }

    @Override
    public void execute() {

    }
}
