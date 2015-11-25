package com.fede.androidsessionlifecycle;

import android.app.Application;
import android.content.res.Resources;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumResources;
import com.leanplum.annotations.Parser;

/**
 * Created by fede on 11/24/15.
 */
public class ApplicationClass extends Application {

//    If you also don't want to extend the LeanplumApplication class, that's fine.
//    It merely acts as a convenience to hold variables and resources.
//    You can implement the same functionality yourself with this code in your own application class.

    @Override
    public void onCreate() {
        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        super.onCreate();
    }

    @Override
    public Resources getResources() {
        return new LeanplumResources(super.getResources());
    }
}
