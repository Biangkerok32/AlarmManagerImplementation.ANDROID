package com.example.sirajulislamsojib.alarmmanagerimplementation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAlarm(View view) {
        Intent intent = new Intent(this,MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 59);

        alarmManager.setWindow(AlarmManager.RTC,calendar.getTimeInMillis(),40000,pendingIntent);

        Toast.makeText(this, "Alarm is set!", Toast.LENGTH_SHORT).show();

    }

    public void stoptAlarm(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,101,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm is stopped!", Toast.LENGTH_SHORT).show();

    }
}
