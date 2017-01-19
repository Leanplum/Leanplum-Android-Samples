package com.leanplum.segmentexample;

import android.app.Application;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushService;
import com.leanplum.segment.LeanplumIntegration;
import com.segment.analytics.Analytics;

/**
 * Created by fede on 1/18/17.
 */

public class ApplicationClass extends Application {

    private static final String SEGMENT_WRITE_KEY = String.valueOf(R.string.SEGMENT_KEY);

    @Override
    public void onCreate(){
        super.onCreate();

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Analytics analytics = new Analytics.Builder(getApplicationContext(), SEGMENT_WRITE_KEY)
                .use(LeanplumIntegration.FACTORY).build();

        analytics.onIntegrationReady("Leanplum",
                new Analytics.Callback() {
                    @Override
                    public void onReady(Object instance) {
                        Leanplum.track("test");
                    }
                });

    }
}
