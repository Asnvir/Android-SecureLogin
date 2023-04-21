package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.baseStrategies.TimeStrategy;
import com.example.securelogin.util.ConditionResult;

public class TimeAndBatteryStrategy implements CombinationStrategy {
    private final String currentTime;
    private final int batteryPercentage;

    public TimeAndBatteryStrategy(String currentTime, int batteryPercentage) {

        this.currentTime = currentTime;
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public ConditionResult isConditionValid() {
        TimeStrategy timeStrategy = new TimeStrategy(currentTime);
        BatteryStrategy batteryStrategy = new BatteryStrategy(batteryPercentage);

        boolean isTimeValid = timeStrategy.isConditionValid().isTimeValid();
        boolean isBatteryValid = batteryStrategy.isConditionValid().isBatteryValid();

        ConditionResult conditionResult = new ConditionResult();
        conditionResult.setTimeChecked(true);
        conditionResult.setBatteryChecked(true);
        conditionResult.setTimeValid(isTimeValid);
        conditionResult.setBatteryValid(isBatteryValid);
        conditionResult.setResult(isTimeValid && isBatteryValid);

        return conditionResult;
    }
}
