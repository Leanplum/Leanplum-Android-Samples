package com.leanplum.android_custompushservice;

import android.app.Application;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;

import static com.leanplum.android_custompushservice.Credentials.AppID;
import static com.leanplum.android_custompushservice.Credentials.DevKey;
import static com.leanplum.android_custompushservice.Credentials.ProdKey;

/**
 * Created by fede on 11/14/16.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);


        // Place your AppID, DevKey and ProdKey here:

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode(AppID, DevKey);
        } else {
            Leanplum.setAppIdForProductionMode(AppID, ProdKey);
        }

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }
}
