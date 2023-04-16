package com.example.securelogin;

import com.example.securelogin.strategies.AllComponentsSelectedStrategy;
import com.example.securelogin.strategies.DefaultStrategy;
import com.example.securelogin.strategies.GpsAndLocationSelectedStrategy;
import com.example.securelogin.strategies.GpsAndTimeSelectedStrategy;
import com.example.securelogin.strategies.GpsSelectedStrategy;
import com.example.securelogin.strategies.LocationSelectedStrategy;
import com.example.securelogin.strategies.NoComponentsSelectedStrategy;
import com.example.securelogin.strategies.TimeAndLocationSelectedStrategy;
import com.example.securelogin.strategies.TimeSelectedStrategy;

public class CombinationStrategyFactory {

    private enum Combination {
        NO_COMPONENTS_SELECTED("0|0|0"),
        LOCATION_SELECTED("0|0|1"),
        TIME_SELECTED("0|1|0"),
        TIME_AND_LOCATION_SELECTED("0|1|1"),
        GPS_SELECTED("1|0|0"),
        GPS_AND_LOCATION_SELECTED("1|0|1"),
        GPS_AND_TIME_SELECTED("1|1|0"),
        ALL_COMPONENTS_SELECTED("1|1|1");

        private final String value;

        Combination(String value) {
            this.value = value;
        }

        public static Combination fromString(String value) {
            for (Combination combination : Combination.values()) {
                if (combination.value.equals(value)) {
                    return combination;
                }
            }
            return null;
        }
    }

    public static CombinationStrategy createCombinationStrategy(String combination) {
        Combination combinationEnum = Combination.fromString(combination);
        if (combinationEnum != null) {
            switch (combinationEnum) {
                case NO_COMPONENTS_SELECTED:
                    return new NoComponentsSelectedStrategy();
                case LOCATION_SELECTED:
                    return new LocationSelectedStrategy();
                case TIME_SELECTED:
                    return new TimeSelectedStrategy();
                case TIME_AND_LOCATION_SELECTED:
                    return new TimeAndLocationSelectedStrategy();
                case GPS_SELECTED:
                    return new GpsSelectedStrategy();
                case GPS_AND_LOCATION_SELECTED:
                    return new GpsAndLocationSelectedStrategy();
                case GPS_AND_TIME_SELECTED:
                    return new GpsAndTimeSelectedStrategy();
                case ALL_COMPONENTS_SELECTED:
                    return new AllComponentsSelectedStrategy();
            }
        }
        return new DefaultStrategy();
    }


}
