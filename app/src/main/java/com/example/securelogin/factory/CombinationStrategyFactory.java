package com.example.securelogin.factory;

public interface CombinationStrategyFactory {
    CombinationStrategy createNoComponentsStrategy();
    CombinationStrategy createTimeStrategy(String time);
    CombinationStrategy createBatteryStrategy(int batteryPercentage);
    CombinationStrategy createTimeAndBatteryStrategy(String time,int batteryPercentage);
    CombinationStrategy createGpsStrategy(double[] coordinates);
    CombinationStrategy createGpsAndTimeStrategy(double[] coordinates,String time);
    CombinationStrategy createGpsAndBatteryStrategy(double[] coordinates,int batteryPercentage);
    CombinationStrategy createGpsAndTimeAndBatteryStrategy(double[] coordinates,String time,int batteryPercentage);
}