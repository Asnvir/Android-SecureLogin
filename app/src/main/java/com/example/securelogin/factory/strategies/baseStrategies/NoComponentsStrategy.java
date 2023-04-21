package com.example.securelogin.factory.strategies.baseStrategies;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.ConditionResult;

public class NoComponentsStrategy implements CombinationStrategy {
    @Override
    public ConditionResult isConditionValid() {

        ConditionResult conditionResult = new ConditionResult();
        conditionResult.setNoComponentsChecked(true);
        conditionResult.setNoComponentsValid(true);
        conditionResult.setResult(true);
        return conditionResult;
    }
}
