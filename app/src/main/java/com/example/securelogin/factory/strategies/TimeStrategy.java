package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class TimeStrategy implements CombinationStrategy {
    private final String currentTime;

    public TimeStrategy(String currentTime) {

        this.currentTime = currentTime;

    }

    @Override
    public boolean isConditionValid() {
        return false;
    }
}
