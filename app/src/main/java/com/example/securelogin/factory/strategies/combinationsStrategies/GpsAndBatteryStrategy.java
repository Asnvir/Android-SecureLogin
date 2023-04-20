package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.GpsStrategy;

public class GpsAndBatteryStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final int batteryPercentage;

    public GpsAndBatteryStrategy(double[] coordinates, int batteryPercentage) {
        this.coordinates=coordinates;
        this.batteryPercentage =batteryPercentage;
    }

    @Override
    public boolean isConditionValid() {
        BatteryStrategy batteryStrategy = new BatteryStrategy(batteryPercentage);
        GpsStrategy gpsStrategy = new GpsStrategy(coordinates);

        boolean isBatteryValid = batteryStrategy.isConditionValid();
        boolean isGpsValid = gpsStrategy.isConditionValid();

        return isBatteryValid && isGpsValid;
    }
}
