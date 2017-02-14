package com.example.shubham.vahan_demo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shubh on 1/31/2017.
 */

public class UnregisterService extends Service {

    CountDownTimer countDownTimer;
    the_receiver the_receiver;
    Context context;
    boolean isTimerRunning = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    /* The code is written in onStartCommand instead of OnCreate because onStartCommand is called
    everytime the service is called by the application and OnCreate is called only once and therefore
    handling the getintent in onCreate results in getting a null value, so the code is in onStartCommand
    function.

    In the Pop up the customer name is fixed as Customer A but the number is updated when a call comes.
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Getting the data from the intent
        if(isTimerRunning){
            countDownTimer.cancel();
        }
        else {
            Toast.makeText(context, "Receiver registered", Toast.LENGTH_SHORT).show();
            getApplication().registerReceiver(the_receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        }
        countDownTimer = new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                //Log.d("srr",String.valueOf(millisUntilFinished));
            }
            public void onFinish() {
                getApplication().unregisterReceiver(the_receiver);
                Toast.makeText(getApplicationContext(), "Receiver unregistered", Toast.LENGTH_SHORT).show();
                countDownTimer = null;
                isTimerRunning = false;
                Toast.makeText(getApplicationContext(), "Service destroyed", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        };
        isTimerRunning = true;
        countDownTimer.start();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
        context = getApplicationContext();
        the_receiver = new the_receiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }


    public class the_receiver extends BroadcastReceiver {
        String msg_from, msgBody = "";

        public the_receiver() {

        }

        @Override
        public void onReceive(Context context, Intent in) {

            try {
                final Bundle bundle = in.getExtras();
                if (bundle != null) {
                    msgBody = "";
                    Log.d("srr bundle", "" + bundle.toString());
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage[] msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        if(msg_from.contains("VAAHAN")) {
                            msgBody += msgs[i].getDisplayMessageBody();
                        }
                        Log.d("srr msg",msgs[i].getDisplayMessageBody());
                    }

                    if (msg_from.contains("VAAHAN")) {
                        Toast.makeText(context, "SMS Arrived!", Toast.LENGTH_SHORT).show();
                        if(dialog_activity.flag){
                            String m = dialog_activity.tv1.getText().toString();
                            m += "\n\n";
                            m += msgBody;
                            dialog_activity.tv1.setText(m);
                        }
                        else{
                            Intent dialogIntent = new Intent(context, dialog_activity.class);
                            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            dialogIntent.putExtra("msg",msgBody);
                            startActivity(dialogIntent);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
