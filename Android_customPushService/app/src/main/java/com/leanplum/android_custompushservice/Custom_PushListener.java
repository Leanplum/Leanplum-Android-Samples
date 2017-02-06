package com.leanplum.android_custompushservice;

import android.os.Bundle;
import android.util.Log;

import com.leanplum.LeanplumPushListenerService;

/**
 * Created by fede on 11/14/16.
 */

public class Custom_PushListener extends LeanplumPushListenerService {

//    @Override
    public void onMessageReceived(String var, Bundle notificationPayload) {
        super.onMessageReceived(var, notificationPayload);

        Log.i("### ", "Received");

    }
}
