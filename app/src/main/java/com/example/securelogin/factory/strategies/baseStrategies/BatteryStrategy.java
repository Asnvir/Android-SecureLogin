package com.example.securelogin.factory.strategies.baseStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.ConditionResult;
import com.example.securelogin.util.Restrictions;

public class BatteryStrategy implements CombinationStrategy {

    private final int batteryPercentage;

    public BatteryStrategy(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public ConditionResult isConditionValid() {
        ConditionResult conditionResult = new ConditionResult();
        int minPercentage = Restrictions.getInstance().getMinPercentage();
        int maxPercentage = Restrictions.getInstance().getMaxPercentage();

        conditionResult.setBatteryChecked(true);
        conditionResult.setBatteryValid(batteryPercentage >= minPercentage && batteryPercentage <= maxPercentage);
        conditionResult.setResult(batteryPercentage >= minPercentage && batteryPercentage <= maxPercentage);

        return  conditionResult;

    }
}
