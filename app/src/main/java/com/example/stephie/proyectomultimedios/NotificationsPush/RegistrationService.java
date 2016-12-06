package com.example.stephie.proyectomultimedios.NotificationsPush;

import android.app.IntentService;
import android.content.Intent;

import com.example.stephie.proyectomultimedios.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by neo_free on 06/12/2016.
 */

public class RegistrationService extends IntentService {
    public RegistrationService(){
        super("RegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID myID = InstanceID.getInstance(this);
        try {
            String registrationToken = myID.getToken(
                    getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                    null
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
