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
            Leanplum.setAppIdForDevelopmentMode("app_pD1WCiarpwpqdnahT6MhGC1KF6L9MAiSn65JQeXQ2C4", "dev_EZtGjDA2BrnXgSqrUDqbfGUQAMsRepWt5BtHVe6cvFo");
        } else {
            Leanplum.setAppIdForProductionMode("app_pD1WCiarpwpqdnahT6MhGC1KF6L9MAiSn65JQeXQ2C4", "prod_3ftu3GJo6IOQhm0ZFtT1BBWjhCiTqwazWdhnszWGgts");
        }

        Leanplum.enableVerboseLoggingInDevelopmentMode();

        Leanplum.setDeviceId("DeviceID_2000");

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }
}
