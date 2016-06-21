package com.leanplum.android_customkeyvaluespair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.leanplum.LeanplumPushListenerService;
import com.leanplum.LeanplumPushReceiver;

/**
 * Created by fede on 5/19/16.
 */
public class CustomPushListenerService extends LeanplumPushListenerService {

    public void onMessageReceived(String var, Bundle notificationPayload) {
        super.onMessageReceived(var, notificationPayload);

        Log.i("#### ", notificationPayload.toString());

        // Checking if is a Leanplum Notification
        // If the "lp_version" string is not null, is a Leanplum notification
        boolean isLeanPlumPushNotification = (notificationPayload.getString("lp_version") != null);

        if (isLeanPlumPushNotification) {
            Log.i("##### ", "LP notification");
            // Code to be executed in case of a Leanplum Notification

            // This code is executed when the Leanplum Notification is received.
            try {
                // With the following the Advanced Data can be retrieved from the Push Notification
                // Be sure the Variable name match - in this sample I'm assuming to set a String variable in the Advanced Data on Dashboard
                String dataString = notificationPayload.getString("String_name");
                // Printing to console the String value
                Log.i("#### ", dataString);
            } catch (NullPointerException e) {
                Log.i("#### ", "No dataString if being specified");

            }

        }
        else {
            Log.i("##### ", "Not a LP notification");
//          // Code to be executed in case of a non-Leanplum Notification
        }



    }
}
