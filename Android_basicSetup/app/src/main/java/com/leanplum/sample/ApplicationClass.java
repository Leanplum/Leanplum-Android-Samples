package com.leanplum.sample;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 4/12/16.
 */
public class ApplicationClass extends Application {

    @Variable
    public static String welcomeString = "welcome message!";


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

        // Registering for Push with Leanplum
        // Here is where the SenderID is passed. In this case I'm using the Leanplum bundle SenderID,
        // no need in this case to specify any specific Google API key in the Settings in the Leanplum Dashboard.
        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        // However, a specific SenderID (or SenderIDs) can be passed for the registration.
        // The SenderID correspond to the Google Cloud Porject number (is a 12 digits number) and needs to be passed as a string.
        // For example:
        //      LeanplumPushService.setGcmSenderId("123456789012");
        // In this case, the Google Cloud Project specific API key needs to be inserted in the Google API key field in the Settings in the Leanplum Dashboard.

        // If using multiple Push services with different SenderIDs, they need to be all passed also to Leanplum, using the following, for example:
        // LeanplumPushService.setGcmSenderIds(LeanplumPushService.LEANPLUM_SENDER_ID, "123456789012", "some other SenderID in string format...");

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("### ", "Welcome message is: " + welcomeString);
            }
        });

        Leanplum.start(this, new StartCallback() {
            @Override
            public void onResponse(boolean b) {
                Log.i("### ", "Leanplum is started - Variants are: " + Leanplum.variants());

            }
        });

    }
}
