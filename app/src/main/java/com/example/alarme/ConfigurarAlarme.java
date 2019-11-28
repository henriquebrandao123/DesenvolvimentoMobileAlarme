package com.example.alarme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ConfigurarAlarme extends AppCompatActivity {


    private ImageView btAccept;
    private ImageView btCancel;
    private TextView txtHora;

    private int currentHour;
    private int currentMinute;
    private int seconds;
    private long time;
    private Calendar c = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_configurar_alarme);

        btAccept = findViewById(R.id.imgAccept);
        btCancel = findViewById(R.id.imgCancel);
        txtHora = findViewById(R.id.txtHora);

        currentHour = c.get(Calendar.HOUR_OF_DAY);
        currentMinute = c.get(Calendar.MINUTE);

        txtHora.setText(currentHour + ":" + currentMinute);



        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                agendar(time);
                finish();

            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerPicker();
            }
        });
    }


    void TimerPicker()
    {

        TimePickerDialog timePickerDialog = new TimePickerDialog(ConfigurarAlarme.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                seconds = (hourOfDay * 3600 +  minute * 60) - (currentHour * 3600 +  currentMinute * 60);

                Calendar c = Calendar.getInstance();

                c.setTimeInMillis(System.currentTimeMillis());

                c.add(Calendar.SECOND, seconds);

                time = c.getTimeInMillis();

                txtHora.setText(hourOfDay + ":" + minute);



            }
        }, currentHour, currentMinute, true);

        timePickerDialog.show();

    }

    public void agendar(long timer)
    {
        Intent it = new Intent("EXECUTAR_ALARME");
        PendingIntent p = PendingIntent.getBroadcast(ConfigurarAlarme.this, 0, it, 0);

        // agendar o alarme
        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarme.set(AlarmManager.RTC_WAKEUP, timer, p);

        Toast.makeText(getApplication(),"Alarme Agendado ", Toast.LENGTH_LONG).show();
        Log.i("Alarme", "Alarme agendado!");
    }

}
