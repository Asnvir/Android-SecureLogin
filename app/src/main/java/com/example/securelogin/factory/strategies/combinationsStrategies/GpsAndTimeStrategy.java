package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.GpsStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.TimeStrategy;
import com.example.securelogin.util.ConditionResult;

public class GpsAndTimeStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final String currentTime;

    public GpsAndTimeStrategy(double[] coordinates, String currentTime) {
        this.coordinates = coordinates;
        this.currentTime = currentTime;

    }

    @Override
    public ConditionResult isConditionValid() {
        GpsStrategy gpsStrategy = new GpsStrategy(coordinates);
        TimeStrategy timeStrategy = new TimeStrategy(currentTime);

        boolean isGpsValid = gpsStrategy.isConditionValid().isGpsValid();
        boolean isTimeValid = timeStrategy.isConditionValid().isTimeValid();

        ConditionResult conditionResult = new ConditionResult();

        conditionResult.setGpsChecked(true);
        conditionResult.setTimeChecked(true);

        conditionResult.setGpsValid(isGpsValid);
        conditionResult.setTimeValid(isTimeValid);

        conditionResult.setResult(isGpsValid && isTimeValid);

        return conditionResult;
    }
}
