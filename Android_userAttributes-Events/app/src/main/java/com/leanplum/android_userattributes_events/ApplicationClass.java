package com.leanplum.android_userattributes_events;


import com.leanplum.Leanplum;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushService;

/**
 * Created by fede on 6/2/16.
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

        Leanplum.enableVerboseLoggingInDevelopmentMode();



        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }
}
