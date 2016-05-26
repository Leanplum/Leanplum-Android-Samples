// Copyright 2014, Leanplum, Inc.

package com.leanplum.customtemplates;

import android.content.Context;

/**
 * Registers all of the built-in message templates.
 * @author Andrew First
 */
public class MessageTemplates {
  static class Args {
    // Open URL
    static final String URL = "URL";
    
    // Alert/confirm arguments.
    static final String TITLE = "Title";
    static final String MESSAGE = "Message";
    static final String ACCEPT_TEXT = "Accept text";
    static final String CANCEL_TEXT = "Cancel text";

    // #### example:
    static final String MAYBE_TEXT = "Maybe text";


    static final String DISMISS_TEXT = "Dismiss text";
    static final String ACCEPT_ACTION = "Accept action";
    static final String CANCEL_ACTION = "Cancel action";
    static final String DISMISS_ACTION = "Dismiss action";
    static final String MAYBE_ACTION = "Maybe action";

    // Center popup/interstitial arguments.
    static final String TITLE_TEXT = "Title.Text";
    static final String TITLE_COLOR = "Title.Color";
    static final String MESSAGE_TEXT = "Message.Text";
    static final String MESSAGE_COLOR = "Message.Color";
    static final String ACCEPT_BUTTON_TEXT = "Accept button.Text";
    static final String ACCEPT_BUTTON_BACKGROUND_COLOR = "Accept button.Background color";
    static final String ACCEPT_BUTTON_TEXT_COLOR = "Accept button.Text color";
    static final String BACKGROUND_IMAGE = "Background image";
    static final String BACKGROUND_COLOR = "Background color";
    static final String LAYOUT_WIDTH = "Layout.Width";
    static final String LAYOUT_HEIGHT = "Layout.Height";
    
    // Web interstitial arguments.
    static final String CLOSE_URL = "Close URL";
    static final String HAS_DISMISS_BUTTON = "Has dismiss button";
  }

  static class Values {
    static final String ALERT_MESSAGE = "Alert message goes here.";
    static final String CONFIRM_MESSAGE = "Confirmation message goes here.";
    static final String POPUP_MESSAGE = "Popup message goes here.";
    static final String INTERSTITIAL_MESSAGE = "Interstitial message goes here.";
    static final String OK_TEXT = "OK";
    static final String YES_TEXT = "Yes";
    static final String NO_TEXT = "No";
    static final String MAYBE_TEXT = "Maybe";
    static final int CENTER_POPUP_WIDTH = 300;
    static final int CENTER_POPUP_HEIGHT = 250;
    
    // Open URL
    static final String DEFAULT_URL = "http://www.example.com";
    
    // Web interstitial values
    static final String DEFAULT_CLOSE_URL = "http://leanplum:close";
    static final boolean DEFAULT_HAS_DISMISS_BUTTON = true;
  }

  private static Boolean registered = false;

  static String getApplicationName(Context context) {
    int stringId = context.getApplicationInfo().labelRes;
    if (stringId == 0) {
      return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    } 
    return context.getString(stringId);
  }

  public synchronized static void register(Context currentContext) {
    if (registered) {
      return;
    }
    registered = true;
    OpenURL.register();
    Alert.register(currentContext);
    Confirm.register(currentContext);
    Confirm3Buttons.register(currentContext);
    CenterPopup.register(currentContext);
    Interstitial.register(currentContext);
    WebInterstitial.register(currentContext);
  }
}
