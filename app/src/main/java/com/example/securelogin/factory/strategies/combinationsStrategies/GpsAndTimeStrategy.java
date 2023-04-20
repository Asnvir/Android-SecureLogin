package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.GpsStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.TimeStrategy;

public class GpsAndTimeStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final String currentTime;

    public GpsAndTimeStrategy(double[] coordinates, String currentTime) {
        this.coordinates = coordinates;
        this.currentTime = currentTime;

    }

    @Override
    public boolean isConditionValid() {
        GpsStrategy gpsStrategy = new GpsStrategy(coordinates);
        TimeStrategy timeStrategy = new TimeStrategy(currentTime);

        boolean isGpsValid = gpsStrategy.isConditionValid();
        boolean isTimeValid = timeStrategy.isConditionValid();

        return isGpsValid && isTimeValid;
    }
}
