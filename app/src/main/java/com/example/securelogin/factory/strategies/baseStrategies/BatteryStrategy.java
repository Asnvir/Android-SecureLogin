package com.example.securelogin.factory.strategies.baseStrategies;

import android.util.Log;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.Restrictions;

public class BatteryStrategy implements CombinationStrategy {

    private final int batteryPercentage;

    public BatteryStrategy(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public boolean isConditionValid() {

        int minPercentage = Restrictions.getInstance().getMinPercentage();
        int maxPercentage = Restrictions.getInstance().getMaxPercentage();
        boolean result = batteryPercentage >= minPercentage && batteryPercentage <= maxPercentage;
        // TODO: Remove logs
        Log.d("battery", "Battery Percentage: " + batteryPercentage);
        Log.d("battery", "Minimum Percentage: " + minPercentage);
        Log.d("battery", "Maximum Percentage: " + maxPercentage);
        Log.d("battery", "isConditionValid: " + result);

        return result;

    }
}
