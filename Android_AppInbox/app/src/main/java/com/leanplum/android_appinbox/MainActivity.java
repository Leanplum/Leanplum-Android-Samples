package com.leanplum.android_appinbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.leanplum.Leanplum;
import com.leanplum.callbacks.InboxChangedCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView unreadCount = findViewById(R.id.unreadCount);

//        Leanplum.newsfeed().addNewsfeedChangedHandler(new NewsfeedChangedCallback() {
//            @Override
//            public void newsfeedChanged() {
//                if (unreadCount != null) {
//                    unreadCount.setText(Leanplum.newsfeed().unreadCount() + "");
//                }
//            }
//        });


        Leanplum.getInbox().addChangedHandler(new InboxChangedCallback() {
            @Override
            public void inboxChanged() {
                if (unreadCount != null) {
                    unreadCount.setText(Leanplum.getInbox().unreadCount() + "");
                }
            }
        });
    }


    public void openList(View view) {
        Intent openList = new Intent(this, InboxList.class);
        startActivity(openList);
    }

    public void RefreshContent(View view){
        Leanplum.forceContentUpdate(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("### ", "Force Content Update done");
            }
        });
    }
}