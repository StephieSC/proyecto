package com.example.stephie.proyectomultimedios;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by neo_free on 07/12/2016.
 */

public class GCMIntentService extends IntentService {

    private static  final int NOTIF_ALERTA_ID = 1;

    public GCMIntentService(){
        super("GCMIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        Bundle extras = intent.getExtras();
        if(!extras.isEmpty()){
            if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType))
            {
                mostrarNotification(extras.getString("message"), extras.getString("title"), Notification.DEFAULT_VIBRATE);
            }
        }
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }
    private void mostrarNotification(String message, String title, int de){

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        mBuilder.setVibrate(new long[]{0, 100, 200, 300});


        Intent notIntent = new Intent(this, MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent, 0);

        mBuilder.setContentIntent(contIntent);
        mNotificationManager.notify(NOTIF_ALERTA_ID,mBuilder.build());


    }
}
