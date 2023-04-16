package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class GpsAndTimeStrategy implements CombinationStrategy {
    private final double[] coordinates;
    private final String currentTime;

    public GpsAndTimeStrategy(double[] coordinates, String currentTime) {
        this.coordinates=coordinates;
        this.currentTime = currentTime;

    }

    @Override
    public void execute() {

    }
}
