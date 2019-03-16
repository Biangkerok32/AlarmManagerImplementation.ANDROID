package com.example.sirajulislamsojib.alarmmanagerimplementation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyAlarmService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Is Running!", Toast.LENGTH_LONG).show();
    }

}
