package com.leanplum.variablessample;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

public class AnotherLPactivity extends LeanplumActivity {

    @Variable
    public static String String_LPactivity = "String Variable defined in AnotherLPactivity";

    static Var<String> mario1 = Var.defineAsset("Mario1", "Mario.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lp);

        final ImageView imageView = (ImageView) findViewById(R.id.welcome_logo_large);

        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### ", String_LPactivity);
                imageView.setImageBitmap(BitmapFactory.decodeStream(mario1.stream()));
            }
        });
    }
}
