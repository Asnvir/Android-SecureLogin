package com.example.securelogin.factory.strategies.combinationsStrategies;

import com.example.securelogin.factory.CombinationStrategy;

public class NoComponentsStrategy implements CombinationStrategy {
    @Override
    public boolean isConditionValid() {

        return true;
    }
}
