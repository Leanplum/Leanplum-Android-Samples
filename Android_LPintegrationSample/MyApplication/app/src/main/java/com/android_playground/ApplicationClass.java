package com.android_playground;
import android.app.Application;
import android.util.Log;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;

import static com.android_playground.Credentials.AppID;
import static com.android_playground.Credentials.DevKey;
import static com.android_playground.Credentials.ProdKey;


public class ApplicationClass extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // This is fundamental for Leanplum basic functionality
        Leanplum.setApplicationContext(this);
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        //

        // Optional
        // deferMessagesForActivities is a method that is typically used when there is a SplashScreen or any other screen where it would make sense to have no in-app messages being displayed (or OpenURL executed).
        LeanplumActivityHelper.deferMessagesForActivities(SplashscreenActivity.class);
        //

        // Optional
        // Parsing classes for variables to be instrumented within Leanplum
        Parser.parseVariablesForClasses(GlobalVariables.class);
        //

        // Optional
        // On Android, default is the Android_ID. In case the Advertising_ID is needed, use the following
        // Leanplum.setDeviceIdMode(LeanplumDeviceIdMode.ADVERTISING_ID);
        //
        if (BuildConfig.DEBUG) {
            Leanplum.enableVerboseLoggingInDevelopmentMode();
            Leanplum.setAppIdForDevelopmentMode(AppID, DevKey);
        } else {
            Leanplum.setAppIdForProductionMode(AppID, ProdKey);
        }

        // Optional
        // Customizing Leanplum Push Notification look
        // Leanplum Customizer is being used in this case to change the App notification icon
//        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
//            @Override
//            public void customize(NotificationCompat.Builder builder, Bundle notificationPayload) {
//                Log.i("#### ", "Icon customized");
//                // Setting a custom smallIcon included in the Drawable folder
//                builder.setSmallIcon(R.drawable.androidbnw);
//            }
//
//            @Override
//            public void customize(Notification.Builder builder, Bundle notificationPayload, @Nullable Notification.Style style) {
//
//            }
//        });
        //

        // Optional
        // Start callback example, code is being executed when Leanplum has started, i.e. when the Leanplum start async call has finished.
        Leanplum.addStartResponseHandler(new StartCallback() {
            @Override
            public void onResponse(boolean b) {
                Log.i("### LP", " - Leanplum started");
            }
        });
        //

        // Starting Leanplum.
        // The 'start' call concludes the initialization part, that needs to be placed inside the Application class
        Leanplum.start(this);
        //
    }
}