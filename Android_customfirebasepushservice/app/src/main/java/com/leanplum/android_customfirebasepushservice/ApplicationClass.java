package com.leanplum.android_customfirebasepushservice;

import android.app.Application;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;

import static com.leanplum.android_customfirebasepushservice.Credentials.AppID;
import static com.leanplum.android_customfirebasepushservice.Credentials.DevKey;
import static com.leanplum.android_customfirebasepushservice.Credentials.ProdKey;

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        if (BuildConfig.DEBUG) {
            //    Leanplum.enableVerboseLoggingInDevelopmentMode();
            Leanplum.setAppIdForDevelopmentMode(AppID, DevKey);
        } else {
            Leanplum.setAppIdForProductionMode(AppID, ProdKey);
        }

        // Firebase!
        LeanplumPushService.enableFirebase();

        Leanplum.start(this);
    }
}