package com.example.securelogin;

import android.app.Application;

import com.example.securelogin.util.Restrictions;
import com.example.securelogin.util.BatteryUtil;


public class Initializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Restrictions.init();

    }
}
