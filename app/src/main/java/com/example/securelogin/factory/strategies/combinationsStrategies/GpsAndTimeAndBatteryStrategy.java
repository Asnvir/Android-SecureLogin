package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.GpsStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.TimeStrategy;

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
        GpsStrategy gpsStrategy = new GpsStrategy(coordinates);
        TimeStrategy timeStrategy = new TimeStrategy(currentTime);
        BatteryStrategy batteryStrategy = new BatteryStrategy(batteryPercentage);

        boolean isGpsValid = gpsStrategy.isConditionValid();
        boolean isTimeValid = timeStrategy.isConditionValid();
        boolean isBatteryValid = batteryStrategy.isConditionValid();

        return isGpsValid && isTimeValid && isBatteryValid;
    }
}
