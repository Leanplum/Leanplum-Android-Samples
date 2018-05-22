package com.leanplum.android_appinbox;

import android.app.Application;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;

/**
 * Created by fede on 1/30/17.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Leanplum.setApplicationContext(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        String appId = Credentials.AppID;
        String devKey = Credentials.DevKey;
        String prodKey = Credentials.ProdKey;

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode(appId, devKey);
        } else {
            Leanplum.setAppIdForProductionMode(appId, prodKey);
        }

        Leanplum.start(this);
    }
}
