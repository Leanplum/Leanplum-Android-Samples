package com.android_playground;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leanplum.Leanplum;

/**
 * Created by fede on 12/7/16.
 */

/**
 * This is a DeepLinked activity example.
 * See the AndroidManifest.xml for the configuration of the 'DeepLinkedActivity' activity.
 */

public class DeepLinkedActivity extends AppCompatActivity {

    public void backToHome(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplinked);

        Leanplum.track("LP DeepLinked activity open");

    }
}
