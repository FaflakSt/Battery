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
    private IntentFilter intentFilter = new IntentFilter();
    private BatteryReceiver receiver = new BatteryReceiver(this, intentFilter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBatteryState = findViewById(R.id.tvBatteryState);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
