package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class GpsAndTimeAndBatteryStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final String currentTime;
    private final int batteryPercentage;

    public GpsAndTimeAndBatteryStrategy(double[] coordinates, String currentTime, int batteryPercentage) {
        this.coordinates = coordinates;
        this.currentTime = currentTime;
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public boolean isConditionValid() {
        return false;
    }
}
