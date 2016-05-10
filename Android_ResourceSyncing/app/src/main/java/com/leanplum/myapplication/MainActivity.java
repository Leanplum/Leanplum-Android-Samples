package com.leanplum.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumResources;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.VariablesChangedCallback;

public class MainActivity extends Activity {

    public void openPage2(View view){
        Intent intent = new Intent(this, Page2Activity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        LeanplumActivityHelper.enableLifecycleCallbacks((Application) getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### ", "Variable changed");

                Drawable resource = getResources().getDrawable(R.drawable.snoopy);
                ImageView imageView = (ImageView) findViewById(R.id.welcome_logo_large);
                imageView.setImageDrawable(resource);
            }
        });
    }

    @Override
    public Resources getResources() {
        return new LeanplumResources(super.getResources());
    }
}
