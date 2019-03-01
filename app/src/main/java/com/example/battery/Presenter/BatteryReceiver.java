package com.example.battery.Presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {

    public interface IBattery {
        void onBatteryStatusChanged(int level, String status);
    }

    private IBattery iBattery;
    private int batteryLevel;
    private String stateChanged = "";
    private String intentBatteryChanged = Intent.ACTION_BATTERY_CHANGED;
    private String intentPowerConnected = Intent.ACTION_POWER_CONNECTED;
    private String intentPowerDisconnected = Intent.ACTION_POWER_DISCONNECTED;
    private String intentBatteryLow = Intent.ACTION_BATTERY_LOW;

    public BatteryReceiver(IBattery iBattery, IntentFilter intentFilter) {
        this.iBattery = iBattery;
        intentFilter.addAction(intentBatteryChanged);
        intentFilter.addAction(intentBatteryLow);
        intentFilter.addAction(intentPowerConnected);
        intentFilter.addAction(intentPowerDisconnected);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            stateChanged = "The device is charging";
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            stateChanged = "The device is not charging";
        } else if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            stateChanged = "Battery is low";
        } else {
            stateChanged = "";
        }

        batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        iBattery.onBatteryStatusChanged(batteryLevel, stateChanged);
    }
}
