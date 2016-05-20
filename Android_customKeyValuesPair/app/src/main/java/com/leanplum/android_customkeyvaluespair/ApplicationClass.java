package com.leanplum.android_customkeyvaluespair;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;

/**
 * Created by fede on 5/19/16.
 */
public class ApplicationClass extends Application {


    @Override
    public void onCreate() {

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        super.onCreate();

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }
}
