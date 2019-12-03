package com.example.alarme.modal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class ManagerAlarm {

    private  static  Intent it = new Intent("EXECUTAR_ALARME");
    private static Calendar c = Calendar.getInstance();
    private static int currentHour;
    private static int currentMinute;



    public static void AgendarAlarme(Context context, int hora, int minuto)
    {
        currentHour = c.get(Calendar.HOUR_OF_DAY);
        currentMinute = c.get(Calendar.MINUTE);

        int seconds = (hora * 3600 +  minuto * 60) ;
        int currentSeconds = (currentHour * 3600 +  currentMinute * 60);
        int difference = 0;

        if(seconds > currentSeconds)
        {
            difference =  seconds - currentSeconds;
        }
        else if(currentSeconds > seconds)
        {
            difference = currentSeconds - seconds + 86400;
        }
        else{
            difference = 0;
        }



        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(System.currentTimeMillis());

        c.add(Calendar.SECOND, difference);


        long time = c.getTimeInMillis();

        Toast.makeText(context," " + difference, Toast.LENGTH_SHORT).show();

        PendingIntent p = PendingIntent.getBroadcast(context, 0, it, 0);

        AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        alarme.set(AlarmManager.RTC_WAKEUP, time, p);



    }

    public static void CancelarAlarme(Context context)
    {
        PendingIntent p = PendingIntent.getBroadcast(context, 0, it, 0);
        AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarme.cancel(p);

    }

}
