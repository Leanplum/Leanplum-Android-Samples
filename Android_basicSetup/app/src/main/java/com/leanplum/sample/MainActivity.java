package com.leanplum.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leanplum.annotations.Variable;

public class MainActivity extends AppCompatActivity {

    @Variable
    public static String mainactivityAtring = "aaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
