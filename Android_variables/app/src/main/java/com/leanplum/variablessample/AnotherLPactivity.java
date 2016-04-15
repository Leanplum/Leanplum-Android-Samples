package com.leanplum.variablessample;

import android.os.Bundle;
import android.util.Log;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lp);

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("#### ", "String_LPactivity: " + String_LPactivity);
            }
        });

    }
}
