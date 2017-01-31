package com.leanplum.android_appinbox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.NewsfeedChangedCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fede on 1/30/17.
 */

public class InboxList extends Activity {

    private static final String TEXT1 = "text1";
    private static final String TEXT2 = "text2";
    public List<String> messagesIds = Leanplum.newsfeed().messagesIds();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inboxlist);

        final Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ListView listView = (ListView) findViewById(R.id.listView);
        ListAdapter listAdapter = createListAdapter();
        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Leanplum.track("Newsfeed Item Clicked!");

                // chekcing if the message is unread
                if (!Leanplum.newsfeed().messageForId(messagesIds.get(i)).isRead()) {
                    Log.i("Leanplum", "Reading getMessageId: " + messagesIds.get(i));
                    Leanplum.newsfeed().messageForId(messagesIds.get(i)).read();


                } else {
                    // Looks like we cannot mark it as unread once is read
                    Log.i("Leanplum", messagesIds.get(i) + " is already read!");
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Leanplum.track("Newsfeed Long Press!");
                Log.i("Leanplum", "Deleting getMessageId: " + messagesIds.get(i));
                Leanplum.newsfeed().messageForId(messagesIds.get(i)).remove();
                return false;
            }
        });

        Leanplum.newsfeed().addNewsfeedChangedHandler(new NewsfeedChangedCallback() {
            @Override
            public void newsfeedChanged() {
                ListAdapter updatedListAdapter = createListAdapter();
                listView.setAdapter(updatedListAdapter);
            }
        });
    }

    private ListAdapter createListAdapter() {
        String[] fromMapKey = new String[] {TEXT1, TEXT2};
        int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
        List<Map<String, String>> list = newsfeedToListItems();
        return new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
    }

    private List<Map<String, String>> newsfeedToListItems() {
        List<Map<String, String>> listItem = new ArrayList<>(Leanplum.newsfeed().count());
        messagesIds = Leanplum.newsfeed().messagesIds();
        for (String messageId : messagesIds) {
            Map<String, String> listItemMap = new HashMap<>();

            if (!Leanplum.newsfeed().messageForId(messageId).isRead()) {
                listItemMap.put(TEXT1, "N - " + Leanplum.newsfeed().messageForId(messageId).getTitle());
            } else {
                listItemMap.put(TEXT1, Leanplum.newsfeed().messageForId(messageId).getTitle());
            }
            listItemMap.put(TEXT2, Leanplum.newsfeed().messageForId(messageId).getSubtitle());
            listItem.add(Collections.unmodifiableMap(listItemMap));
        }
        return Collections.unmodifiableList(listItem);
    }
}