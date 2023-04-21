package com.example.securelogin.util;

public class Restrictions {

    private static Restrictions instance;
    private final double[] coordinates ;
    private final double maxDistance ;
    private final int minPercentage ;
    private final int maxPercentage ;
    private final String time;


    private Restrictions() {
        coordinates = new double[]{33.32424461545233, 34.853230991079904};
        maxDistance = 1;
        minPercentage = 50;
        maxPercentage = 60;
        time = "2023-04-21 23:38";
    }

    public static void init() {
        if (instance == null) {
            instance = new Restrictions();
        }
    }

    public static Restrictions getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Restrictions not initialized. Call init() first.");
        }
        return instance;
    }

    public double getTargetLatCoordinate() {
        return coordinates[0];
    }

    public double getTargetLonCoordinate() {
        return coordinates[1];
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public int getMinPercentage() {
        return minPercentage;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }

    public String getTargetTime() {
        return time;
    }
}
