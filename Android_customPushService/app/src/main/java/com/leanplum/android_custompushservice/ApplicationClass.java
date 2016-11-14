package com.leanplum.android_custompushservice;

import android.app.Application;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushService;

/**
 * Created by fede on 11/14/16.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("APP_KEY", "DEV_KEY");
        } else {
            Leanplum.setAppIdForProductionMode("APP_KEY", "PROD_KEY");
        }

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }
}
