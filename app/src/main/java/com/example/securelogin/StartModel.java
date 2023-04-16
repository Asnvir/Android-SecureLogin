package com.example.securelogin;

public class StartModel {
    private final LocationHandler locationHandler;


    public StartModel (LocationHandler locationHandler){
        this.locationHandler = locationHandler;
    }

    public void requestLocationUpdates() {
        locationHandler.requestLocationUpdates();
    }

    public void removeLocationUpdates() {
        locationHandler.removeLocationUpdates();
    }

    public void login(Boolean isGpsSelected, Boolean isTimeSelected, Boolean isLocationSelected) {
        String combination = (isGpsSelected ? "1" : "0") + "|" + (isTimeSelected ? "1" : "0") + "|" + (isLocationSelected ? "1" : "0");

        // Используем стратегию для выполнения действий в зависимости от комбинации
        CombinationStrategy strategy = CombinationStrategyFactory.createCombinationStrategy(combination);
        strategy.performActions();
    }
}


//    public void login() {
//        boolean isGpsSelected = this.isGpsSelected.getValue() != null && this.isGpsSelected.getValue();
//        boolean isTimeSelected = this.isTimeSelected.getValue() != null && this.isTimeSelected.getValue();
//        boolean isLocationSelected = this.isLocationSelected.getValue() != null && this.isLocationSelected.getValue();
//
//        String combination = (isGpsSelected ? "1" : "0") + "|" + (isTimeSelected ? "1" : "0") + "|" + (isLocationSelected ? "1" : "0");
//
//        switch (combination) {
//            case "0|0|0":
//                // Выполнить действия, когда ни один из компонентов не выбран
//                break;
//            case "0|0|1":
//                // Выполнить действия для выбранного местоположения, но не выбранных GPS и времени
//                break;
//            case "0|1|0":
//                // Выполнить действия для выбранного времени, но не выбранных GPS и местоположения
//                break;
//            case "0|1|1":
//                // Выполнить действия для выбранного GPS и времени, но не выбранного местоположения
//                break;
//            case "1|0|0":
//                // Выполнить действия для выбранного GPS, но не выбранных времени и местоположения
//                break;
//            case "1|0|1":
//                // Выполнить действия для выбранного GPS и местоположения, но не выбранного времени
//                break;
//            case "1|1|0":
//                // Выполнить действия для выбранного GPS и времени, но не выбранного местоположения
//                break;
//            case "1|1|1":
//                // Выполнить действия для успешного входа, если все три компонента выбраны
//                break;
//            default:
//                // Выполнить действия для остальных необработанных комбинаций
//                break;
//        }
//    }
