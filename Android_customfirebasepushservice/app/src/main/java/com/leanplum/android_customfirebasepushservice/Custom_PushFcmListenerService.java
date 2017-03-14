package com.leanplum.android_customfirebasepushservice;

import android.content.Intent;
import android.util.Log;

import com.leanplum.LeanplumPushFcmListenerService;
import com.leanplum.LeanplumPushRegistrationService;

/**
 * Created by fede on 3/13/17.
 */

public class Custom_PushFcmListenerService extends LeanplumPushFcmListenerService {

    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();
    }

}
