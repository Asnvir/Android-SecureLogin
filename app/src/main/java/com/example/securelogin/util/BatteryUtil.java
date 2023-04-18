package com.example.securelogin.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryUtil {

    private static BatteryUtil instance;
    private static Context context; // Changed to non-static field

    private BatteryUtil() {
        // Private constructor to prevent instantiation
    }

    // Singleton instance retrieval method
    public static BatteryUtil getInstance() {
        if (instance == null) {
            instance = new BatteryUtil();
        }
        return instance;
    }

    // Initialize with Context
    public static void init(Context context) { // Changed to non-static method
        BatteryUtil.context = context;
    }

    // Function to get the battery percentage
    public int getBatteryPercentage() {
        if (context == null) {
            throw new IllegalStateException("Context not initialized. Call init(Context) before using.");
        }
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, filter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level * 100 / (float) scale;
        return Math.round(batteryPct);
    }
}
