package com.example.alarme;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class Alarme extends AppCompatActivity {

    private Button btCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme);
        btCancelar = findViewById(R.id.btCancelar);

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long milliseconds = 10000;
        long[] machaImperial = new long[]{500,110,500,110,450,110,200,110,170,40,450,110,200,110,170,40,500};
        //vibrator.vibrate(milliseconds);
        vibrator.vibrate(machaImperial,-1);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
            }
        });
    }



}
