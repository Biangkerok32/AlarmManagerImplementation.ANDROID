package com.example.sirajulislamsojib.alarmmanagerimplementation;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText_id);

    }

    public void startAlarm(View view) {
        Intent intent = new Intent(this,MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                111,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date customDate = formatter.parse(editText.getText().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(customDate);

            Calendar newCalendar = Calendar.getInstance();
            newCalendar.set(
                    calendar.get(calendar.YEAR),
                    calendar.get(calendar.MONTH),
                    calendar.get(calendar.DAY_OF_MONTH),
                    calendar.get(calendar.HOUR_OF_DAY),
                    calendar.get(calendar.MINUTE),
                    calendar.get(calendar.SECOND)

            );

            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            Toast.makeText(this, "Alarm is set!", Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            Toast.makeText(this, "Problem to set Alarm!", Toast.LENGTH_SHORT).show();
        }
    }

    public void stoptAlarm(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,MyAlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,111,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm is stopped!", Toast.LENGTH_SHORT).show();

    }
}
