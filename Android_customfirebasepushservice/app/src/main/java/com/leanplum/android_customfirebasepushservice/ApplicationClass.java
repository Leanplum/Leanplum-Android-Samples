package com.leanplum.android_customfirebasepushservice;

import android.app.Application;
import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;
import com.leanplum.callbacks.StartCallback;

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

        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
            @Override
            public void customize(NotificationCompat.Builder builder, Bundle notificationPayload) {
                Log.i("### LP " , "notification customized");
                // Setting a custom smallIcon included in the Drawable folder
                builder.setSmallIcon(R.drawable.androidbnw);
            }
            @Override
            public void customize(Notification.Builder builder, Bundle bundle, @Nullable Notification.Style style) {

            }
        });

        Leanplum.start(this, new StartCallback() {
            @Override
            public void onResponse(boolean b) {
                Leanplum.setUserId("FedeTestPush");
            }
        });
    }
}