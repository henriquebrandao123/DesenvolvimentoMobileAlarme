package com.example.alarme;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.Vibrator;

public class Alarme extends AppCompatActivity {

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme);

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long milliseconds = 10000;
        vibrator.vibrate(milliseconds);
    }



}
