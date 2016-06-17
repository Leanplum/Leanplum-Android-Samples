package com.leanplum.variablessample;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumDeviceIdMode;
import com.leanplum.LeanplumPushService;
import com.leanplum.Var;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by fede on 4/15/16.
 */
public class ApplicationClass extends Application {

    // Defining Variables using Annotations

    // String_Welcome1 and String_Welcome2 are going to be displayed on Screen - see MainActivity class
    @Variable
    public static String String_Welcome1 = "Welcome to Leanplum!";
    @Variable
    public static String String_Welcome2 = "You can change those text strings overriding 'String_Welcome1' and 'String_Welcome2' values on the Dashboard variables";

    @Variable
    public static Map<String, Object> powerup = new HashMap<String, Object>() {
        {
            put("name", "Turbo Boost");
            put("price", 150);
            put("speedMultiplier", 1.5);
            put("timeout", 15);
            put("slots", Arrays.asList(1, 2, 3));
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        Parser.parseVariablesForClasses(AnotherActivity.class, AnotherLPactivity.class);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        Leanplum.enableVerboseLoggingInDevelopmentMode();

        // Example of a Variable defined inside the onCreate method
        final Var<String> welcomeLabel = Var.define("welcomeLabel", "Welcome!");

        // Registering Leanplum - Fill in your APP_ID and KEYs
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {

                // Printing to console the values of some Variables
                Log.i("#### ", welcomeLabel.value());
                for (Map.Entry<String, Object> entry : powerup.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    Log.i("#### ", "Application class var : " + key + " " + value.toString());
                }
            }
        });

        Leanplum.start(this);
    }
}
