package com.android_playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.StartCallback;

/**
 * Created by fede on 12/7/16.
 */

public class SplashscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // When Leanplum has started, the following Callback is going to execute.
        // In this case, it means that the SplashScreen is going to be dismissed and opening the MainActivity only when Leanplum start call has finished
        Leanplum.addStartResponseHandler(new StartCallback() {
            @Override
            public void onResponse(boolean b) {
                Log.i("### LP", " - Splashscreen  - Leanplum has started and variables are retrieved");

                Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

    }
}
