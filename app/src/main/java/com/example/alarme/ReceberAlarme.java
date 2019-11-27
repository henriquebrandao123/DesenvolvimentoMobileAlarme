package com.example.alarme;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class ReceberAlarme  extends BroadcastReceiver {

    public void onReceive(Context c, Intent i)
    {
        Toast.makeText(c, "Alarme!!!!", Toast.LENGTH_SHORT).show();
        Intent i2 = new Intent(c, Alarme.class);
        i2.putExtra(Alarme.EXTRA_LIGAR_E_DESTRAVAR_TELA, true);
        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i2);
    }
}
