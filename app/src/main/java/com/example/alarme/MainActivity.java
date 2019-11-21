package com.example.alarme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ServiceWorkerController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {

    private Button btAlarme;
    private TextView status;

    private int currentHour;
    private int currentMinute;
    private int seconds;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.txtStatus);
        btAlarme = findViewById(R.id.btAlarme);

        btAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();

                currentHour = c.get(Calendar.HOUR_OF_DAY);
                currentMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        seconds = (hourOfDay * 3600 +  minute * 60) - (currentHour * 3600 +  currentMinute * 60) ;

                        Calendar c = Calendar.getInstance();

                        c.setTimeInMillis(System.currentTimeMillis());

                        c.add(Calendar.SECOND, seconds);

                        time = c.getTimeInMillis();

                        status.setText(""+seconds);
                        agendar(time);




                    }
                }, currentHour, currentMinute, true);

                timePickerDialog.show();



                //finish();
            }
        });
    }


    public void agendar(long timer)
    {
        Intent it = new Intent("EXECUTAR_ALARME");
        PendingIntent p = PendingIntent.getBroadcast(MainActivity.this, 0, it, 0);

        // agendar o alarme

        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarme.set(AlarmManager.RTC_WAKEUP, timer, p);

        Toast.makeText(getApplication(),"Alarme Agendado ", Toast.LENGTH_LONG).show();
        Log.i("Alarme", "Alarme agendado!");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
