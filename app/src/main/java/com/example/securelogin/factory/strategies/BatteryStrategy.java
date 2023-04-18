package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.Restrictions;

public class BatteryStrategy implements CombinationStrategy {

    private final int batteryPercentage;

    public BatteryStrategy(int batteryPercentage){
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public boolean isConditionValid() {

        int minPercentage = Restrictions.getInstance().getMinPercentage();
        int maxPercentage = Restrictions.getInstance().getMaxPercentage();
        return  batteryPercentage >= minPercentage && batteryPercentage <= maxPercentage;

    }
}
