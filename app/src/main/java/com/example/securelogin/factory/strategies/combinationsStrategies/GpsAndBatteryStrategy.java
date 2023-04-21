package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.GpsStrategy;
import com.example.securelogin.util.ConditionResult;

public class GpsAndBatteryStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final int batteryPercentage;

    public GpsAndBatteryStrategy(double[] coordinates, int batteryPercentage) {
        this.coordinates=coordinates;
        this.batteryPercentage =batteryPercentage;
    }

    @Override
    public ConditionResult isConditionValid() {
        BatteryStrategy batteryStrategy = new BatteryStrategy(batteryPercentage);
        GpsStrategy gpsStrategy = new GpsStrategy(coordinates);

        boolean isBatteryValid = batteryStrategy.isConditionValid().isBatteryValid();
        boolean isGpsValid = gpsStrategy.isConditionValid().isGpsValid();

        ConditionResult conditionResult = new ConditionResult();
        conditionResult.setGpsChecked(true);
        conditionResult.setBatteryChecked(true);
        conditionResult.setBatteryValid(isBatteryValid);
        conditionResult.setGpsValid(isGpsValid);
        conditionResult.setResult(isBatteryValid && isGpsValid);

        return conditionResult;
    }
}
