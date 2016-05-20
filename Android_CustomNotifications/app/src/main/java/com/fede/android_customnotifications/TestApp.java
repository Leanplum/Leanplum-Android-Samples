package com.fede.android_customnotifications;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;
import com.leanplum.LeanplumResources;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.util.Arrays;

/**
 * Created by fede on 9/30/15.
 */
public class TestApp extends LeanplumApplication {

    @Override
    public void onCreate(){

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        super.onCreate();


        // Insert here your App Leanplum keys

        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("APP_KEY", "DEV_KEY");
        } else {
            Leanplum.setAppIdForProductionMode("APP_KEY", "PROD_KEY");
        }

        // The customizer is set in the Application Class
        // In this case, I'm modifying the Notification Small Icon, Large Icon and attaching an image beneath as well
        
        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
                    //
                    @Override
                    public void customize(NotificationCompat.Builder builder, Bundle notificationPayload) {

                        builder.setSmallIcon(R.drawable.atest);

                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.androidorange);
                        builder.setLargeIcon(largeIcon);

                        Bitmap androidBanner = BitmapFactory.decodeResource(getResources(), R.drawable.androidappsdev);
                        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidBanner));

                    }
                });


        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);


    }
}
