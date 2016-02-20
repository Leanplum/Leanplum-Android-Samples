package com.fede.proguardtest;

import android.os.Bundle;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushService;

/**
 * Created by fede on 2/17/16.
 */
public class MainApplication extends LeanplumApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // Insert here your App Leanplum keys
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

    }
}
