package com.leanplum.android_customkeyvaluespair;

import android.os.Bundle;
import android.util.Log;

import com.leanplum.LeanplumPushListenerService;

/**
 * Created by fede on 5/19/16.
 */
public class CustomPushListenerService extends LeanplumPushListenerService {

    public void onMessageReceived(String var, Bundle data) {
        super.onMessageReceived(var, data);

        // This code is executed when the Notification is received.

        // With the following the Advanced Data can be retrieved from the Push Notification
        // Be sure the Variable name match - in this sample I'm assuming to set a String variable in the Advanced Data on Dashboard
        String dataString = data.getString("String_name");
        // Printing to console the String value
        Log.i("#### ", dataString);

    }
}
