package com.leanplum.segmentexample;
import android.app.Application;
import android.util.Log;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.segment.LeanplumIntegration;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fede on 1/18/17.
 */

public class ApplicationClass extends Application {

    // Segmemt.io (f.casali@gmail.com)
//    private static final String ANALYTICS_WRITE_KEY_DEV = "WV0IGPMEneSe74l0lRoLaOAaGj7S4pbM";
    private static final String ANALYTICS_WRITE_KEY_DEV = "4kLittwcaUKQBzmROW0D7GBRlH3EFjoL";
    private static final String ANALYTICS_WRITE_KEY_PROD = "DnxT5cAonrfRKiHJrYC9suZKmfH2ul7o";


    @Override
    public void onCreate(){
        super.onCreate();

        Leanplum.setApplicationContext(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

//        Leanplum.trackAllAppScreens();

        // Enabling Push
        // GCM
//        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);
        //Firebase
//        LeanplumPushService.enableFirebase();


        Analytics.Builder builder;

        if (BuildConfig.DEBUG) {
            builder = new Analytics.Builder(this, ANALYTICS_WRITE_KEY_DEV)
                            .trackApplicationLifecycleEvents()
                            .trackAttributionInformation()
                            .recordScreenViews()
                            .use(LeanplumIntegration.FACTORY);

        } else {
            builder = new Analytics.Builder(this, ANALYTICS_WRITE_KEY_PROD)
                            .trackApplicationLifecycleEvents()
                            .trackAttributionInformation()
                            .recordScreenViews()
                            .use(LeanplumIntegration.FACTORY);
        }



        // Set the initialized instance as a globally accessible instance.
        Analytics.setSingletonInstance(builder.build());
        // Now anytime you call Analytics.with, the custom instance will be returned.
        final Analytics analytics = Analytics.with(this);


        analytics.onIntegrationReady(LeanplumIntegration.LEANPLUM_SEGMENT_KEY,
                new Analytics.Callback() {
                    @Override
                    public void onReady(Object instance) {
                        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
                            @Override
                            public void variablesChanged() {
                                Log.i("### ", "Leanplum started");
                            }
                        });
                    }
                });



        Leanplum.addStartResponseHandler(new StartCallback() {
            @Override
            public void onResponse(boolean b) {
                // this is not working for Leanplum
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("name", "federico1");
                params.put("age", 990);
                Analytics.with(getApplicationContext()).track("testEvent1", new Properties().putValue("testKey", params));

//                                // --
                Properties myProps = new Properties();
                myProps.putValue("name","federico2");
                myProps.putValue("age", 991);
                Analytics.with(getApplicationContext()).track("testEvent2", myProps);
            }
        });
    }
}