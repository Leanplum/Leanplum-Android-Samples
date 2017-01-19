package com.leanplum.segmentexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushService;
import com.leanplum.segment.LeanplumIntegration;
import com.segment.analytics.Analytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
