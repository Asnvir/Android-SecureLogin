package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class BatteryStrategy implements CombinationStrategy {

    private final int batteryPercentage;

    public BatteryStrategy(int batteryPercentage){
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public void execute() {

    }
}
