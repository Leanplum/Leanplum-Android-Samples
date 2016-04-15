package com.leanplum.variablessample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leanplum.Leanplum;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.annotations.Variable;

public class MainActivity extends LeanplumActivity {

    public void openAnotherActivity(View view) {
        Intent intent = new Intent(this, AnotherActivity.class);
        startActivity(intent);
    }

    public void openLPactivity(View view) {
        Intent intent = new Intent(this, AnotherLPactivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
