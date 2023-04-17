package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class NoComponentsStrategy implements CombinationStrategy {
    @Override
    public boolean isConditionValid() {
        return false;
    }
}
