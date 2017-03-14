package com.leanplum.android_customfirebasepushservice;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.leanplum.LeanplumPushFirebaseMessagingService;

/**
 * Created by fede on 3/13/17.
 */

public class Custom_FirebaseMessagingService extends LeanplumPushFirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       super.onMessageReceived(remoteMessage);

        Log.i("### ", "Firebase message received");
    }

}
