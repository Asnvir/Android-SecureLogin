package com.example.securelogin.factory.strategies.baseStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.ConditionResult;
import com.example.securelogin.util.Restrictions;


public class TimeStrategy implements CombinationStrategy {
    private final String currentTime;

    public TimeStrategy(String currentTime) {

        this.currentTime = currentTime;

    }

    @Override
    public ConditionResult isConditionValid() {
        ConditionResult conditionResult = new ConditionResult();

        conditionResult.setTimeChecked(true);
        conditionResult.setTimeValid(Restrictions.getInstance().getTargetTime().equals(currentTime));
        conditionResult.setResult(Restrictions.getInstance().getTargetTime().equals(currentTime));

        return conditionResult;
    }
}
