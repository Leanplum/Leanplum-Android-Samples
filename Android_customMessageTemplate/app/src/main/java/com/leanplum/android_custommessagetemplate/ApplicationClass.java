package com.leanplum.android_custommessagetemplate;

import android.os.Bundle;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumApplication;

/**
 * Created by fede on 5/25/16.
 */
public class ApplicationClass extends LeanplumApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

        com.leanplum.customtemplates.MessageTemplates.register(getApplicationContext());

        Leanplum.start(this);
    }

}
