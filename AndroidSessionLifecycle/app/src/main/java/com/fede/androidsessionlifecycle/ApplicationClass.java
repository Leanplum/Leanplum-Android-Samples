package com.fede.androidsessionlifecycle;

import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;
import com.leanplum.LeanplumResources;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 11/24/15.
 */
public class ApplicationClass extends Application {

    @Variable
    public static String welcomeMessage = "Welcome to Leanplum!";

//    If you also don't want to extend the LeanplumApplication class, that's fine.
//    It merely acts as a convenience to hold variables and resources.
//    You can implement the same functionality yourself with this code in your own application class.

    // To do so, just add in the onCreate method the following:
    //    Leanplum.setApplicationContext(this);
    //    Parser.parseVariables(this);
    //    LeanplumActivityHelper.enableLifecycleCallbacks(this);


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        // Insert here your App Leanplum keys
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("Leanplum", "####" + welcomeMessage);
            }
        });
    }

    // This is needed in case Resources Syncing needs to be enabled
//
//    @Override
//    public Resources getResources() {
//        return new LeanplumResources(super.getResources());
//    }
}
