package com.example.battery.View;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.battery.Presenter.BatteryReceiver;
import com.example.battery.R;

public class MainActivity extends AppCompatActivity implements BatteryReceiver.IBattery {

    private TextView tvBatteryState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBatteryState = findViewById(R.id.tvBatteryState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        BatteryReceiver receiver = new BatteryReceiver(this);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onBatteryStatusChanged(int level, String status) {

        if (!status.equals("")) {
            Toast.makeText(MainActivity.this, status, Toast.LENGTH_LONG).show();
        }

        String percentage = String.valueOf(level);
        tvBatteryState.setText(percentage + "%");
    }
}
