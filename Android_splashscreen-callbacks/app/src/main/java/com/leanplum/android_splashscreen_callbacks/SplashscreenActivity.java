package com.leanplum.android_splashscreen_callbacks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 8/23/16.
 */
public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("### ", "All variables changed and no download is pending");

                Log.i("#### ", "Leanplum started");

                Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });



//        Leanplum.addStartResponseHandler(new StartCallback() {
//            @Override
//            public void onResponse(boolean b) {
//
//                Log.i("#### ", "Leanplum started");
//
//                Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });
    }

}
