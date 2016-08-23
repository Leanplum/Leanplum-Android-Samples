package com.leanplum.android_splashscreen_callbacks;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.VariablesChangedCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.welcome_logo_large);

//        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
//            @Override
//            public void variablesChanged() {
//                Log.i("### ", "All variables changed and no download is pending");

                if (imageView != null) {
                    imageView.setImageBitmap(BitmapFactory.decodeStream(GlobalVariables.mario1.stream()));
                }
//            }
//        });
    }
}
