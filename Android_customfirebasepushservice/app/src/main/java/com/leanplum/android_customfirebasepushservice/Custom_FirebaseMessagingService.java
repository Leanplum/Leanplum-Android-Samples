package com.leanplum.android_customfirebasepushservice;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.leanplum.LeanplumPushFirebaseMessagingService;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;

/**
 * Created by fede on 3/13/17.
 */

public class Custom_FirebaseMessagingService extends LeanplumPushFirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        boolean isLeanPlumPushNotification = (remoteMessage.getData().containsKey("lp_version"));

        if (isLeanPlumPushNotification) {
            Log.i("### ", "LP notification: " + String.valueOf(isLeanPlumPushNotification));
            // Code to be executed in case of a Leanplum Notification

            super.onMessageReceived(remoteMessage);

            try {
                // With the following the Advanced Data can be retrieved from the Push Notification
                // Be sure the Variable name match - in this sample I'm assuming to set a String variable in the Advanced Data on Dashboard
                String dataString = remoteMessage.getData().get("String_name");
                // Printing to console the String value
                Log.i("### LP payload ", dataString);
            } catch (NullPointerException e) {
                Log.i("### LP ", "No extra data String is being specified");
            }
        }
        else {
            Log.i("##### ", "Not a LP notification");
//          // Code to be executed in case of a non-Leanplum Notification
        }


    }

}
