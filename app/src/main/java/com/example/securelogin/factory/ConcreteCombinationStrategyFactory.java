package com.example.securelogin.factory;

import com.example.securelogin.factory.strategies.BatteryStrategy;
import com.example.securelogin.factory.strategies.GpsAndBatteryStrategy;
import com.example.securelogin.factory.strategies.GpsAndTimeAndBatteryStrategy;
import com.example.securelogin.factory.strategies.GpsAndTimeStrategy;
import com.example.securelogin.factory.strategies.GpsStrategy;
import com.example.securelogin.factory.strategies.NoComponentsStrategy;
import com.example.securelogin.factory.strategies.TimeAndBatteryStrategy;
import com.example.securelogin.factory.strategies.TimeStrategy;

public class ConcreteCombinationStrategyFactory implements CombinationStrategyFactory {

    public CombinationStrategy createCombinationStrategy(String combination,double[] coordinates, String time, int batteryPercentage) {
        CombinationStrategy strategy = null;

        switch (combination) {
            case "0|0|0":
                strategy = createNoComponentsStrategy();
                break;
            case "0|0|1":
                strategy = createTimeStrategy(time);
                break;
            case "0|1|0":
                strategy = createBatteryStrategy(batteryPercentage);
                break;
            case "0|1|1":
                strategy = createTimeAndBatteryStrategy(time, batteryPercentage);
                break;
            case "1|0|0":
                strategy = createGpsStrategy(coordinates);
                break;
            case "1|0|1":
                strategy = createGpsAndTimeStrategy(coordinates,time);
                break;
            case "1|1|0":
                strategy = createGpsAndBatteryStrategy(coordinates,batteryPercentage);
                break;
            case "1|1|1":
                strategy = createGpsAndTimeAndBatteryStrategy(coordinates,time,batteryPercentage);
                break;
            default:
                break;
        }

        return strategy;
    }


    @Override
    public CombinationStrategy createNoComponentsStrategy() {
        return new NoComponentsStrategy();
    }

    @Override
    public CombinationStrategy createTimeStrategy(String time) {
        return new TimeStrategy(time);
    }

    @Override
    public CombinationStrategy createBatteryStrategy(int batteryPercentage) {
        return new BatteryStrategy(batteryPercentage);
    }

    @Override
    public CombinationStrategy createTimeAndBatteryStrategy(String time,int batteryPercentage) {
        return new TimeAndBatteryStrategy(time,batteryPercentage);
    }

    @Override
    public CombinationStrategy createGpsStrategy(double[] coordinates) {
        return new GpsStrategy(coordinates);
    }

    @Override
    public CombinationStrategy createGpsAndTimeStrategy(double[] coordinates,String time) {
        return new GpsAndTimeStrategy(coordinates,time);
    }

    @Override
    public CombinationStrategy createGpsAndBatteryStrategy(double[] coordinates,int batteryPercentage) {
        return new GpsAndBatteryStrategy(coordinates,batteryPercentage);
    }

    @Override
    public CombinationStrategy createGpsAndTimeAndBatteryStrategy(double[] coordinates,String time,int batteryPercentage) {
        return new GpsAndTimeAndBatteryStrategy(coordinates,time,batteryPercentage);
    }



}
// Дополнительный метод для создания CombinationStrategy на основе комбинации компонентов
//    public CombinationStrategy createCombinationStrategy(String combination) {
//        // Ваша логика для создания CombinationStrategy на основе переданной комбинации
//        // Может быть реализована здесь
//        // Например:
//        if ("0|0|0".equals(combination)) {
//            return createNoComponentsStrategy();
//        } else if ("0|0|1".equals(combination)) {
//            return createTimeStrategy();
//        } else if ("0|1|0".equals(combination)) {
//            return createBatteryStrategy();
//        } else if ("0|1|1".equals(combination)) {
//            return createTimeAndBatteryStrategy();
//        } else if ("1|0|0".equals(combination)) {
//            return createGpsStrategy();
//        } else if ("1|0|1".equals(combination)) {
//            return createGpsAndTimeStrategy();
//        } else if ("1|1|0".equals(combination)) {
//            return createGpsAndBatteryStrategy();
//        } else if ("1|1|1".equals(combination)){
//            return createGpsAndTimeAndBatteryStrategy();
//        }
//        return null;
//    }