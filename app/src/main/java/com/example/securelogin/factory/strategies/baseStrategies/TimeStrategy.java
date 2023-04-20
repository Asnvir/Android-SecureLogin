package com.example.securelogin.factory.strategies.baseStrategies;

import android.util.Log;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.Restrictions;
import com.example.securelogin.util.TimeUtil;

public class TimeStrategy implements CombinationStrategy {
    private final String currentTime;

    public TimeStrategy(String currentTime) {

        this.currentTime = currentTime;

    }

    @Override
    public boolean isConditionValid() {
        boolean result = Restrictions.getInstance().getTargetTime().equals(currentTime);
        // TODO: Remove logs
        Log.d("time", "Current Time: " + TimeUtil.getCurrentTime());
        Log.d("time", "Target Time: " + currentTime);
        Log.d("time", "isConditionValid: " + result);
        return result;
    }
}
