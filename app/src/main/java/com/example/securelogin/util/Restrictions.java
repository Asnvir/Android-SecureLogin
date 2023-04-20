package com.example.securelogin.util;

public class Restrictions {

    private static Restrictions instance;  // Singleton instance

    private final double[] coordinates ;
    private final double maxDistance ;
    private final int minPercentage ;
    private final int maxPercentage ;
    private String time;

    // Private constructor to prevent direct instantiation
    private Restrictions() {
        coordinates = new double[]{32.32424461545233, 34.853230991079904};
        maxDistance = 201;
        minPercentage = 50;
        maxPercentage = 100;
        time = "2023-04-20 00:15";
    }

    // Method to initialize the singleton instance
    public static void init() {
        if (instance == null) {
            instance = new Restrictions();
        }
    }

    // Method to get the singleton instance
    public static Restrictions getInstance() {
        if (instance == null) {
            // Throw an exception or return a default instance if not initialized
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
