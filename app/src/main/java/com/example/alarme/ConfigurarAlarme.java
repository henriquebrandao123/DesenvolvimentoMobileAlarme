package com.example.alarme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarme.modal.ManagerAlarm;
import com.example.alarme.modal.PreferencesAlarme;

import java.util.Calendar;

public class ConfigurarAlarme extends AppCompatActivity {


    private static final int REQ_CODE_PICK_SOUNDFILE =  1;
    private ImageView btAccept;
    private ImageView btCancel;
    private TextView txtHora;
    private Button btToque;
    private int hora;
    private int minuto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_configurar_alarme);

        btAccept = findViewById(R.id.imgAccept);
        btCancel = findViewById(R.id.imgCancel);
        txtHora = findViewById(R.id.txtHora);
        btToque = findViewById(R.id.btToque);

        hora = 19;
        minuto = 21;

        txtHora.setText(hora + ":" + minuto);

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ManagerAlarm.AgendarAlarme(v.getContext(), hora, minuto);
                PreferencesAlarme.setStatusAlarme(v.getContext(), "status", 1);

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

        btToque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }


    void TimerPicker() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(ConfigurarAlarme.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                hora = hourOfDay;
                minuto = minute;

                if (hora < 10)
                    txtHora.setText("0" + hourOfDay + ":" + minute);
                if (minuto < 10)
                    txtHora.setText(hourOfDay + ":" + "0" + minute);
                if (hora < 10 && minuto < 10)
                    txtHora.setText("0" + hourOfDay + ":" + "0" + minute);
                if (hora > 10 || minuto > 10)
                    txtHora.setText(hourOfDay + ":" + minute);


                PreferencesAlarme.setHoraAlarme(ConfigurarAlarme.this, "hora", txtHora.getText().toString());


            }
        }, 19, 21, true);

        timePickerDialog.show();

    }

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mpeg");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_audio_file_title)), REQ_CODE_PICK_SOUNDFILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_PICK_SOUNDFILE && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
        }
    }
}
