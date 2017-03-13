package com.leanplum.android_customgcmpushservice;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.leanplum.LeanplumPushListenerService;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;

/**
 * Created by fede on 11/14/16.
 */

// Custom_PushListener class is the Service being placed in the AndroidManifest.xml associated with
    //  <intent-filter>
//          <action android:name="com.google.android.c2dm.intent.RECEIVE" />
//      </intent-filter>

public class Custom_PushListener extends LeanplumPushListenerService {

    // Optional:
    // Customizing the Push Notification placing the Customizer inside onCreate()
    @Override
    public void onCreate(){
        super.onCreate();

        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
            @Override
            public void customize(NotificationCompat.Builder builder, Bundle notificationPayload) {
//              Setting a custom smallIcon included in the Drawable folder
                builder.setSmallIcon(R.drawable.atest);
            }
        });
    }

    @Override
    public void onMessageReceived(String var, Bundle notificationPayload) {

        // This would ensure the Leanplum code is executed once a Push Notification message is received
        super.onMessageReceived(var, notificationPayload);

        // Following code would be executed other than the Leanplum default behavior
        Log.i("### ", "Push received");

    }
}
