package com.example.securelogin.factory.strategies;

import com.example.securelogin.factory.CombinationStrategy;

public class GpsStrategy implements CombinationStrategy {
    private final double[] coordinates;

    public GpsStrategy(double[] coordinates) {
        this.coordinates=coordinates;


    }

    @Override
    public void execute() {

    }
}
