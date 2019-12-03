package com.example.alarme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarme.modal.ManagerAlarm;
import com.example.alarme.modal.PreferencesAlarme;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  {

    private TextView status;
    private TextView txtAlarme;
    private Switch btDesligar ;
    private FloatingActionButton btAdd1;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd1 = findViewById(R.id.btAdd);
        btDesligar = findViewById(R.id.switchDesativar);
        txtAlarme = findViewById(R.id.txtAlarme);

       if(PreferencesAlarme.getStatusAlarme(this,"status") == 1)
       {
           btDesligar.setChecked(true);
       }
       else{

            btDesligar.setChecked(false);
       }


        btAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ConfigurarAlarme.class);
                startActivity(intent);

            }
        });

        btDesligar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   if(PreferencesAlarme.getStatusAlarme(buttonView.getContext(),"status") == 0)
                   {
                       String  horaAlarme = PreferencesAlarme.getHoraAlarme(buttonView.getContext(),"hora");
                       ManagerAlarm.AgendarAlarme(buttonView.getContext(),Integer.parseInt(horaAlarme.substring(0,2)),Integer.parseInt(horaAlarme.substring(3)));

                   }

                   Toast.makeText(getApplication(),"Alarme Ativado", Toast.LENGTH_SHORT).show();

                }
                else
                {
                  Toast.makeText(getApplication(),"Alarme Desativado", Toast.LENGTH_SHORT).show();
                  PreferencesAlarme.setStatusAlarme(buttonView.getContext(),"status",0);
                  ManagerAlarm.CancelarAlarme(buttonView.getContext());
                }
            }
        });

       txtAlarme.setText(PreferencesAlarme.getHoraAlarme(this,"hora"));
    }


    @Override
    protected void onResume() {
        super.onResume();
        txtAlarme.setText(PreferencesAlarme.getHoraAlarme(this,"hora"));
        if(PreferencesAlarme.getStatusAlarme(this,"status") == 1){
            btDesligar.setChecked(true);
        }
        else {
            btDesligar.setChecked(false);
        }


   }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
