package com.example.alarme.modal;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesAlarme {

    //private static Context context;

    private String horaMinuto;
    private String tempo;
    private String melodia;



    public final static String PREFS_NAME = "appname_prefs";


    public  static void setHoraAlarme(Context context,String key, String value){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getHoraAlarme(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"0");
    }

    public static  void setStatusAlarme(Context context,String key, int  value){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public static int getStatusAlarme(Context context, String key)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return Integer.parseInt(prefs.getString(key,"0"));
    }



}
