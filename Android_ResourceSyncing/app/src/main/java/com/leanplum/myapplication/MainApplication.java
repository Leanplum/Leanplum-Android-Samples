package com.leanplum.myapplication;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushService;
import com.leanplum.LeanplumResources;
import com.leanplum.Var;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fede on 4/4/16.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate(){
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);


        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("", "");
        } else {
            Leanplum.setAppIdForProductionMode("", "");
        }

        Leanplum.enableVerboseLoggingInDevelopmentMode();

        Leanplum.syncResourcesAsync(Arrays.asList("drawable/s.*"), null);

        Leanplum.start(this);
    }
}
