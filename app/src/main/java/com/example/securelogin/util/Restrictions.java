package com.example.securelogin.util;

public class Restrictions {

    private static Restrictions instance;  // Singleton instance

    private double[] coordinates;
    private int minPercentage;
    private int maxPercentage;
    private String time;

    // Private constructor to prevent direct instantiation
    private Restrictions() {
        // Initialize default values for coordinates, minPercentage, maxPercentage, time, etc.
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



    public double[] getCoordinates() {
        return coordinates;
    }

    public int getMinPercentage() {
        return minPercentage;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }

    public String getTime() {
        return time;
    }
}
