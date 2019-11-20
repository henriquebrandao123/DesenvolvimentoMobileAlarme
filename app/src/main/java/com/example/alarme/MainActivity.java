package com.example.alarme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {

    private Button btAlarme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAlarme = findViewById(R.id.btAlarme);
        btAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    }
                }, 0, 0, false);

                timePickerDialog.show();
               
                //agendar();
                //finish();
            }
        });
    }


    public void agendar()
    {
        Intent it = new Intent("EXECUTAR_ALARME");
        PendingIntent p = PendingIntent.getBroadcast(MainActivity.this, 0, it, 0);
        // precisamos pegar agora + 10segundos

        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(System.currentTimeMillis());

        c.add(Calendar.SECOND, 10); // +10 segundos


        // agendar o alarme

        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);

        long time = c.getTimeInMillis();

        alarme.set(AlarmManager.RTC_WAKEUP, time, p);



        // debug:
        Toast.makeText(getApplication(),"Alarme Agendado ", Toast.LENGTH_LONG).show();
        Log.i("Alarme", "Alarme agendado!");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
