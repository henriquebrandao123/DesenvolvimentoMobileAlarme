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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {

    private TextView status;
    private FloatingActionButton btAdd1;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd1 = findViewById(R.id.btAdd);

        btAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ConfigurarAlarme.class);
                startActivity(intent);

            }
        });


/*
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
        });*/
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
