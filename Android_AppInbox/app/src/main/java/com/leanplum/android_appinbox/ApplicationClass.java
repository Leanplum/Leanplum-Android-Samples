package com.leanplum.android_appinbox;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;

/**
 * Created by fede on 1/30/17.
 */

public class ApplicationClass extends Application {

    private static String AppID = "APP_ID";
    private static String DevKey = "DEV_KEY";
    private static String ProdKey = "PROD_KEY";

    @Override
    public void onCreate(){
        super.onCreate();

        Leanplum.setApplicationContext(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);


        Parser.parseVariables(this);

        String appId = AppID;
        String devKey = DevKey;
        String prodKey = ProdKey;

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode(appId, devKey);
        } else {
            Leanplum.setAppIdForProductionMode(appId, prodKey);
        }

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.trackAllAppScreens();

        Leanplum.start(this);
    }
}
