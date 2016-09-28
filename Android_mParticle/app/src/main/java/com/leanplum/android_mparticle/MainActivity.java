package com.leanplum.android_mparticle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.mparticle.MPEvent;
import com.mparticle.MParticle;
import com.mparticle.kits.LeanplumKit;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static EditText mEdit;
    public static String userID;


    public void setUserID(View view){
        mEdit = (EditText) findViewById(R.id.loginField);
        userID = mEdit.getText().toString();
        Leanplum.setUserId(userID);
        Log.i("### ", "Leanplum setUserID with user: " + userID);
        userID = null;
    }

    Map<String, String> eventInfo = new HashMap<String, String>(2);
    Map<String, Object> params = new HashMap<String, Object>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("### ", "Leanplum variable: " + LPvariables.LPstring);
                Log.i("### ", "Leanplum variable: " + String.valueOf(LPvariables.LPint));
            }
        });

        Leanplum.addStartResponseHandler(new StartCallback() {
            @Override
            public void onResponse(boolean b) {

                Log.i("### ", "Leanplum started");

                // Loggin an event using Leanplum - you should see this event tracked in Leanplum
                params.put("post", 12345);
                Leanplum.track("Leanplum started", params);

                // Tracking events using MParticle - you should also see those events tracked in Leanplum
                MParticle.getInstance().logEvent("Mparticle tracked event - other", MParticle.EventType.Other);
                MParticle.getInstance().logEvent("Mparticle tracked event - location", MParticle.EventType.Location);

                eventInfo.put("spice", "hot");
                eventInfo.put("menu", "weekdays");

                MPEvent event = new MPEvent.Builder("Food Order", MParticle.EventType.Transaction)
                        .duration(100)
                        .info(eventInfo)
                        .build();

                MParticle.getInstance().logEvent(event);


            }
        });

    }
}



