package com.leanplum.android_appinbox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumInbox;
import com.leanplum.callbacks.InboxChangedCallback;

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
    public LeanplumInbox inbox = Leanplum.getInbox();
    public List<String> messagesIds = inbox.messagesIds();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inboxlist);

        final ListView listView = (ListView) findViewById(R.id.listView);
        ListAdapter listAdapter = createListAdapter();
        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Leanplum.track("App Inbox Item Clicked!");

                // checking if the message is unread
                if (!inbox.messageForId(messagesIds.get(i)).isRead()) {
                    Log.i("Leanplum", "Reading getMessageId: " + messagesIds.get(i));
                    inbox.messageForId(messagesIds.get(i)).read();

                } else {
                    // Looks like we cannot mark it as unread once is read
                    Log.i("Leanplum", messagesIds.get(i) + " is already read!");
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Leanplum.track("App Inbox Long Press!");
                Log.i("Leanplum", "Deleting getMessageId: " + messagesIds.get(i));
                inbox.messageForId(messagesIds.get(i)).remove();
                return false;
            }
        });

        inbox.addChangedHandler(new InboxChangedCallback() {
            @Override
            public void inboxChanged() {
                ListAdapter updatedListAdapter = createListAdapter();
                listView.setAdapter(updatedListAdapter);
            }
        });
    }

    private ListAdapter createListAdapter() {
        String[] fromMapKey = new String[] {TEXT1, TEXT2};
        int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
        List<Map<String, String>> list = inboxToListItems();
        return new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
    }

    private List<Map<String, String>> inboxToListItems() {
        List<Map<String, String>> listItem = new ArrayList<>(inbox.count());
        messagesIds = inbox.messagesIds();
        for (String messageId : messagesIds) {
            Map<String, String> listItemMap = new HashMap<>();

            if (!inbox.messageForId(messageId).isRead()) {
                listItemMap.put(TEXT1, "N - " + inbox.messageForId(messageId).getTitle());
            } else {
                listItemMap.put(TEXT1, inbox.messageForId(messageId).getTitle());
            }
            listItemMap.put(TEXT2, inbox.messageForId(messageId).getSubtitle());
            listItem.add(Collections.unmodifiableMap(listItemMap));
        }
        return Collections.unmodifiableList(listItem);
    }
}