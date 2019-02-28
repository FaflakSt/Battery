package com.example.battery.Presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReceiver extends BroadcastReceiver {

    private int batteryLevel;
    private String stateChanged = "";

    public interface IBattery {
        void onBatteryStatusChanged(int level, String status);
    }

    private IBattery iBattery;

    public BatteryReceiver(IBattery iBattery) {
        this.iBattery = iBattery;
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
