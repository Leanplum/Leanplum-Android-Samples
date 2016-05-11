package com.leanplum.variablessample;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 4/15/16.
 */
public class AnotherLPactivity extends LeanplumActivity {

    @Variable
    public static String String_LPactivity = "String var in LPactivity";

    static Var<String> mario1 = Var.defineAsset("Mario1", "Mario.png");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lp);

//        final String String_test = getString(R.string.string_name);


        final ImageView im = new ImageView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(im);

        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### ", "String_LPactivity: " + String_LPactivity);

//                im.setImageBitmap(BitmapFactory.decodeStream(ApplicationClass.mario.stream()));

                im.setImageBitmap(BitmapFactory.decodeStream(mario1.stream()));

//                Log.i("#### ", String_test);

            }
        });

    }
}
