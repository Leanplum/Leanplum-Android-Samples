package com.leanplum.variablessample;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 4/15/16.
 */
public class AnotherActivity extends Activity {

    @Variable
    public static String String_AnotherActivity = "String var in AnotherActivity";

    static Var<String> mario2 = Var.defineAsset("Mario2", "Mario.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        final ImageView im = new ImageView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(im);

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### ", "String_AnotherActivity: " + String_AnotherActivity);

                im.setImageBitmap(BitmapFactory.decodeStream(mario2.stream()));
            }
        });
    }


}
