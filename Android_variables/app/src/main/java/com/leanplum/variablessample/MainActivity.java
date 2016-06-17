package com.leanplum.variablessample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

public class MainActivity extends LeanplumActivity {

    public void openAnotherActivity(View view) {
        // Opening a Non-Leanplum Activity
        Intent intent = new Intent(this, AnotherActivity.class);
        startActivity(intent);
    }

    public void openLPactivity(View view) {
        // Opening another Leanplum Activity
        Intent intent = new Intent(this, AnotherLPactivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView welcomeMessageText1 = (TextView) findViewById(R.id.textView4);
        final TextView welcomeMessageText2 = (TextView) findViewById(R.id.textView5);

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                // Printing to screen variables defined in the Application class
                welcomeMessageText1.setText(ApplicationClass.String_Welcome1);
                welcomeMessageText2.setText(ApplicationClass.String_Welcome2);
            }
        });
    }
}