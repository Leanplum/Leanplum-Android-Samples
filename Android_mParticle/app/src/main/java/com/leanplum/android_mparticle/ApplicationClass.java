package com.leanplum.android_mparticle;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.mparticle.MParticle;

/**
 * Created by fede on 9/19/16.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Parsing for Variable to be registered in Leanplum Dashboard
        // This has to be done BEFORE starting Leanplum
        // Variables are defined in this case into LPvariables class
        Parser.parseVariables(this);
        Parser.parseVariablesForClasses(LPvariables.class);


        // Registering for Push with Leanplum
        // Here is where the SenderID is passed. In this case I'm using the Leanplum bundle SenderID,
        // no need in this case to specify any specific Google API key in the Settings in the Leanplum Dashboard.
        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        // However, a specific SenderID (or SenderIDs) can be passed for the registration.
        // The SenderID correspond to the Google Cloud Porject number (is a 12 digits number) and needs to be passed as a string.
        // For example:
//              LeanplumPushService.setGcmSenderId("123456789012");
        // In this case, the Google Cloud Project specific API key needs to be inserted in the Google API key field in the Settings in the Leanplum Dashboard.

        // If using multiple Push services with different SenderIDs, they need to be all passed also to Leanplum, using the following, for example:
        // LeanplumPushService.setGcmSenderIds(LeanplumPushService.LEANPLUM_SENDER_ID, "123456789012", "some other SenderID in string format...");


        // Starting MParticle - this will also start Leanplum

        // Starting mParticle and Leanplum in Production mode
//        MParticle.start(this, MParticle.InstallType.AutoDetect, MParticle.Environment.Production);

        // Starting mParticle and Leanplum in Development mode
//        MParticle.start(this, MParticle.InstallType.AutoDetect, MParticle.Environment.Development);

        // mParticle keys are stored in res/values/string.xml
        MParticle.start(this);

    }
}