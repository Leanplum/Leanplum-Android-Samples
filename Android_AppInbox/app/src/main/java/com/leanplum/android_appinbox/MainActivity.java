package com.leanplum.android_appinbox;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.NewsfeedMessage;
import com.leanplum.callbacks.NewsfeedChangedCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView unreadCount = (TextView) findViewById(R.id.unreadCount);
        Leanplum.newsfeed().addNewsfeedChangedHandler(new NewsfeedChangedCallback() {
            @Override
            public void newsfeedChanged() {
                if (unreadCount != null) {
                    unreadCount.setText(Leanplum.newsfeed().unreadCount() + "");
                }
            }
        });
    }

    public void openList(View view) {
        Intent openList = new Intent(this, InboxList.class);
        startActivity(openList);
    }
}