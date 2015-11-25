package com.fede.androidsessionlifecycle;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.Var;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

public class MainActivity extends AppCompatActivity {

    // All variables must be defined before calling Leanplum.start.
    @Variable
    public static String welcomeMessage = "Welcome to Leanplum!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


// Leanplum needs to keep track of when the user's session is active in order to calculate time spent in the app.
// If all of your activities extend LeanplumActivity, you don't need to worry about managing the session lifecycle, as Leanplum will do it for you.
// However, if for some reason you cannot extend our activity class, you need to include some extra code in your activity.
//
// Apps targeting Android OS 4.0 and higher do not need to implement these lifecycle callbacks - see Docs: https://www.leanplum.com/docs#/docs/android
// Instead, using a LeanplumApplication superclass or adding this line of code is sufficient: LeanplumActivityHelper.enableLifecycleCallbacks(this);

        LeanplumActivityHelper.enableLifecycleCallbacks(this.getApplication());


        // We've inserted your FedeApp3 API keys here for you :)
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_aDBIMk9nN4VH0zabSwtZ61Rz8RxfDIoQbXrchEikZXs", "dev_XPFus0gB0BlVUe0DCwcE4XDvZzs671SDVPZDIMjQwxI");
        } else {
            Leanplum.setAppIdForProductionMode("app_aDBIMk9nN4VH0zabSwtZ61Rz8RxfDIoQbXrchEikZXs", "prod_fG8RRLU3SaJTFSaEaoqCpXTTpVNHOFU2Qv6UhygSnY8");
        }

        Leanplum.setDeviceId("device003");

        // It's important to use the variables changed callback if the value is needed
        // around the time the app starts, so that we're guaranteed to have the latest value.
        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### Test", welcomeMessage);
            }
        });


        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
