package com.example.shubham.vahan_demo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by shubh on 1/31/2017.
 */

public class MainActivity extends AppCompatActivity {

    private Button I1, I2, I3, I4, I5;
    private PendingIntent sentIntents;
    private PendingIntent deliveryIntents;
    private String msg;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(MainActivity.this,UnregisterService.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        I1 = (Button)findViewById(R.id.button1);
        I2 = (Button)findViewById(R.id.button2);
        I3 = (Button)findViewById(R.id.button3);
        I4 = (Button)findViewById(R.id.button4);
        I5 = (Button)findViewById(R.id.button5);

        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = "VAHAN " + "No1";
                //sendSMS(msg);
                startService(i);
                Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
            }
        });

        I2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = "VAHAN " + "No2";
                //sendSMS(msg);
                startService(i);
                Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
            }
        });

        I3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = "VAHAN " + "No3";
                //sendSMS(msg);
                startService(i);
                Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
            }
        });

        I4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = "VAHAN " + "No4";
                //sendSMS(msg);
                startService(i);
                Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
            }
        });

        I5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = "VAHAN " + "No5";
                //sendSMS(msg);
                startService(i);
                Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendSMS(String message){

        SmsManager sm = SmsManager.getDefault();
        sentIntents = PendingIntent.getBroadcast(this, 0,
                new Intent("SMS_SENT"), 0);
        deliveryIntents = PendingIntent.getBroadcast(this, 0,
                new Intent("SMS_DELIVERED"), 0);
        MainActivity.this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        startService(i);
                        Toast.makeText(context, "SMS Sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "SMS Sending Failed-Generic Failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context, "SMS Sending Failed-No Service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "SMS Sending Failed-Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context, "SMS Sending Failed-Radio Off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT"));
        sm.sendTextMessage("+917738299899", null, message, sentIntents, deliveryIntents);
        Log.d("srr",message);
    }
}
