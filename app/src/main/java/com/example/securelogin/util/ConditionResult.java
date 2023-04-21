package com.example.securelogin.util;

public class ConditionResult {

    private boolean noComponentsValid;
    private  boolean noComponentsChecked;
    private boolean gpsChecked ;
    private boolean batteryChecked;
    private boolean timeChecked ;

    private boolean gpsValid ;
    private boolean batteryValid ;
    private boolean timeValid ;

    private boolean result;

    public ConditionResult() {
        gpsChecked = false;
        batteryChecked = false;
        timeChecked = false;
        noComponentsChecked = false;
        gpsValid = false;
        batteryValid = false;
        timeValid = false;
        noComponentsValid = false;
        result = false;
    }

    public boolean isGpsChecked() {
        return gpsChecked;
    }

    public void setGpsChecked(boolean gpsChecked) {
        this.gpsChecked = gpsChecked;
    }

    public boolean isBatteryChecked() {
        return batteryChecked;
    }

    public void setBatteryChecked(boolean batteryChecked) {
        this.batteryChecked = batteryChecked;
    }

    public boolean isTimeChecked() {
        return timeChecked;
    }

    public void setTimeChecked(boolean timeChecked) {
        this.timeChecked = timeChecked;
    }

    public boolean isGpsValid() {
        return gpsValid;
    }

    public void setGpsValid(boolean gpsValid) {
        this.gpsValid = gpsValid;
    }

    public boolean isBatteryValid() {
        return batteryValid;
    }

    public void setBatteryValid(boolean batteryValid) {
        this.batteryValid = batteryValid;
    }

    public boolean isTimeValid() {
        return timeValid;
    }

    public void setTimeValid(boolean timeValid) {
        this.timeValid = timeValid;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isNoComponentsChecked() {
        return noComponentsChecked;
    }

    public void setNoComponentsChecked(boolean noComponentsChecked) {
        this.noComponentsChecked = noComponentsChecked;
    }

    public boolean isNoComponentsValid() {
        return noComponentsValid;
    }

    public void setNoComponentsValid(boolean noComponentsValid) {
        this.noComponentsValid = noComponentsValid;
    }

}
