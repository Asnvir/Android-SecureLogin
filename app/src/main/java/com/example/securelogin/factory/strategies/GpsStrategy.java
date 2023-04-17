package com.example.securelogin.factory.strategies;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.Restrictions;

public class GpsStrategy implements CombinationStrategy {

    private final double[] coordinates;

    public GpsStrategy(double[] coordinates) {
        this.coordinates=coordinates;


    }

    // Helper method to calculate distance between two sets of coordinates using Haversine formula
    private double calculateDistance() {
        double latUser = coordinates[0];
        double lonUser = coordinates[1];
        double latTarget = Restrictions.getInstance().getLatCoordinate();
        double lonTarget = Restrictions.getInstance().getLonCoordinate();

        // Call Location.distanceBetween() method to calculate distance
        float[] results = new float[1];
        Location.distanceBetween(latUser, lonUser, latTarget, lonTarget, results);
        float distance = results[0]; // distance in meters

        return (double) distance; // Cast distance to double and return
    }


    @Override
    public boolean isConditionValid() {
        double currentDistance = calculateDistance();
        // TODO: 18/04/2023 Убрать логи
//        Log.d("distance", "User Coordinate: " + coordinates[0] + ", " + coordinates[1]);
//        Log.d("distance", "Target Coordinate: " + Restrictions.getInstance().getLatCoordinate() + ", " + Restrictions.getInstance().getLonCoordinate());
//        Log.d("distance", "Calculated Distance: " + currentDistance + " meters");
//        Log.d("distance", "Maximum Distance: " +  Restrictions.getInstance().getMaxDistance() + " meters");
        boolean result = currentDistance < Restrictions.getInstance().getMaxDistance();
//        Log.d("distance", "isConditionValid: " + result);
        return result;
    }

}
