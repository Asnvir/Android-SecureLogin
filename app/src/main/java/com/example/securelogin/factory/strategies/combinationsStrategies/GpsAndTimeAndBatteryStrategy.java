package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.GpsStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.TimeStrategy;
import com.example.securelogin.util.ConditionResult;

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
    public ConditionResult isConditionValid() {
        GpsStrategy gpsStrategy = new GpsStrategy(coordinates);
        TimeStrategy timeStrategy = new TimeStrategy(currentTime);
        BatteryStrategy batteryStrategy = new BatteryStrategy(batteryPercentage);

        boolean isGpsValid = gpsStrategy.isConditionValid().isGpsValid();
        boolean isTimeValid = timeStrategy.isConditionValid().isTimeValid();
        boolean isBatteryValid = batteryStrategy.isConditionValid().isBatteryValid();

        ConditionResult conditionResult = new ConditionResult();

        conditionResult.setGpsChecked(true);
        conditionResult.setTimeChecked(true);
        conditionResult.setBatteryChecked(true);

        conditionResult.setGpsValid(isGpsValid);
        conditionResult.setTimeValid(isTimeValid);
        conditionResult.setBatteryValid(isBatteryValid);

        conditionResult.setResult(isGpsValid && isTimeValid && isBatteryValid);

        return conditionResult;
    }
}
