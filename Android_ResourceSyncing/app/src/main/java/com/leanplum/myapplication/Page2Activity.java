package com.leanplum.myapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 5/10/16.
 */
public class Page2Activity extends LeanplumActivity {

    public void displayImage() {

        Drawable resource = getResources().getDrawable(R.drawable.snoopy);

        Log.i("#### ", "displayImage function called");
        ImageView imageView = (ImageView) findViewById(R.id.welcome_logo_large);
        imageView.setImageDrawable(resource);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### ", "Page2Activity callback");
                displayImage();
            }
        });
    }
}
