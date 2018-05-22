package com.leanplum.segmentexample;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.segment.analytics.Analytics;
import com.segment.analytics.Traits;


public class MainActivity extends AppCompatActivity {

    public static EditText mEdit;
    public static EditText getmEdit() {
        return mEdit;
    }


    public void login(View view){
        mEdit = findViewById(R.id.textView2);
        Traits data_traits = new Traits()
                .putName("First Last")
                .putEmail("first@last.com");

        Analytics.with(this).identify(mEdit.getText().toString(), data_traits, null);

        Leanplum.forceContentUpdate(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Intent intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onResume() {
        super.onResume();
        Traits login_traits = new Traits().putValue("isLoggedIn","False");
        Analytics.with(this).identify(login_traits);
        login_traits.remove("isLoggedIn");
    }

}
