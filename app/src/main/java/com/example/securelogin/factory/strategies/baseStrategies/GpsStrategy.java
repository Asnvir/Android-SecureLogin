package com.example.securelogin.factory.strategies.baseStrategies;

import android.location.Location;

import com.example.securelogin.factory.CombinationStrategy;
import com.example.securelogin.util.ConditionResult;
import com.example.securelogin.util.Restrictions;

public class GpsStrategy implements CombinationStrategy {

    private final double[] coordinates;

    public GpsStrategy(double[] coordinates) {
        this.coordinates=coordinates;
    }

    private double calculateDistance() {
        double latUser = coordinates[0];
        double lonUser = coordinates[1];
        double latTarget = Restrictions.getInstance().getTargetLatCoordinate();
        double lonTarget = Restrictions.getInstance().getTargetLonCoordinate();

        float[] results = new float[1];
        Location.distanceBetween(latUser, lonUser, latTarget, lonTarget, results);
        float distance = results[0];
        return (double) distance;
    }


    @Override
    public ConditionResult isConditionValid() {
        ConditionResult conditionResult = new ConditionResult();
        double currentDistance = calculateDistance();

        conditionResult.setGpsChecked(true);
        conditionResult.setGpsValid(currentDistance < Restrictions.getInstance().getMaxDistance());
        conditionResult.setResult(currentDistance < Restrictions.getInstance().getMaxDistance());

        return conditionResult;
    }

}
