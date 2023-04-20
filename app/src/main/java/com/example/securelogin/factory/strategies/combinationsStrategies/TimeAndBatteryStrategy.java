package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.TimeStrategy;

public class TimeAndBatteryStrategy implements CombinationStrategy {
    private final String currentTime;
    private final int batteryPercentage;

    public TimeAndBatteryStrategy(String currentTime, int batteryPercentage) {

        this.currentTime = currentTime;
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public boolean isConditionValid() {
        TimeStrategy timeStrategy = new TimeStrategy(currentTime);
        BatteryStrategy batteryStrategy = new BatteryStrategy(batteryPercentage);

        boolean isTimeValid = timeStrategy.isConditionValid();
        boolean isBatteryValid = batteryStrategy.isConditionValid();


        return isTimeValid && isBatteryValid;
    }
}
