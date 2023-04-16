package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class GpsAndBatteryStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final int batteryPercentage;

    public GpsAndBatteryStrategy(double[] coordinates, int batteryPercentage) {
        this.coordinates=coordinates;
        this.batteryPercentage =batteryPercentage;
    }

    @Override
    public void execute() {

    }
}
