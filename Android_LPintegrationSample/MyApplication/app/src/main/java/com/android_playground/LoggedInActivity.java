package com.android_playground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.VariablesChangedCallback;
import java.util.HashMap;
import java.util.Map;

import static com.android_playground.GlobalVariables.welcomeString;

/**
 * Created by fede on 12/7/16.
 */

public class LoggedInActivity extends AppCompatActivity {

    Map<String, Object> loginAttribute = new HashMap<String, Object>();
    Map<String, Object> UA = new HashMap<String, Object>();
    Map<String, Object> songParams = new HashMap<String, Object>();
    Map<String, Object> purchaseParams = new HashMap<String, Object>();

    @Override
    public void onBackPressed(){
        Log.i("### LP" , "- Back button disabled - click on Logout to return to Login");
    };

    public void setUA(View view) {

        UA.put("name", "federico");
        UA.put("item number", 12345);
        UA.put("boolean", true);

        Leanplum.setUserAttributes(UA);
    }

    public void trackArtist(View view){
        songParams.put("Album", "Cosmic Egg");
        songParams.put("Song", "New Moon Rising");

        Leanplum.track("Wolfmother", songParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        Leanplum.track("loggedin");

        // Setting a User Attribute to "flag" the user as being logged in
        loginAttribute.put("isLoggedIn", true);
        Leanplum.setUserAttributes(loginAttribute);

        EditText medit = MainActivity.getmEdit();
        Log.i("### LP", " - User is now logged in with User: " + medit.getText().toString());

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                // Some code executed here
                // In PRODUCTION this code would be executed, however the Variables data is being FETCHED
                // only ONCE - i.e. after Leanplum has started.
                TextView tv = findViewById(R.id.textView);
                tv.setText(welcomeString);
            }
        });
    }

    public void logout(View view) {
        this.finish();
    }

    public void trackPurchase(View view) {
        /*
        * This is an example of a Purchase event.
        * You can specify any Purchase name you want to adopt, but would have to be consistent - i.e. all purchase events should have the same Purchase event name.
         * We recommend to use 'Leanplum.PURCHASE_EVENT_NAME'.
         *
         * Value is the float number to be used for the purchase value.
         *
         * Any other information regarding the specific Purchase Event can be passed as an event Parameter, just like any normal event, in this case the 'purchaseParams' object.
        */
        purchaseParams.put("number", 1234);
        purchaseParams.put("ingredient1", "mushrooms");
        purchaseParams.put("ingredient2", "prosciutto");
        purchaseParams.put("extra", "napkins");
        Leanplum.track(Leanplum.PURCHASE_EVENT_NAME, 19.99, purchaseParams);
    }
}