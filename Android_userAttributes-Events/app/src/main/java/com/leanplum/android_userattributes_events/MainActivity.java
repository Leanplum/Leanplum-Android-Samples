package com.leanplum.android_userattributes_events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leanplum.Leanplum;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static Map<String, Object> attributes = new HashMap<String, Object>();
    static Map<String, Object> params = new HashMap<String, Object>();

    // Setting some User Attributes to the current User
    public static void setUserAttrib_1(View view){
        attributes.put("gender", "Female");
        attributes.put("age", 30);
        Leanplum.setUserAttributes(attributes);
        Log.i("### Leanplum: ", "Set User Attributes \"gender\", \"Female\" and \"age\", 30");
    }

    // Setting some other values for the same User Attributes than above
    public static void setUserAttrib_2(View view){
        attributes.put("gender", "Male");
        attributes.put("age", 36);
        Leanplum.setUserAttributes(attributes);
        Log.i("### Leanplum: ", "Set User Attributes \"gender\", \"Male\" and \"age\", 36");
    }


    // Tracking an event called "Test_Event" passing a parameter
    public static void trackEvent(View view){
        // Adding a Parameter number and passing it along the Event
        params.put("Test_Param", 12345);
        Leanplum.track("Test_Event", params);
        Log.i("### Leanplum: ", "\"Test_Event\" event tracked with parameters \"12345\"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
