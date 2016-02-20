package com.fede.proguardtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.activities.LeanplumActivity;
import com.leanplum.callbacks.StartCallback;

public class MainActivity extends LeanplumActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Leanplum.start(this);
    }
}
