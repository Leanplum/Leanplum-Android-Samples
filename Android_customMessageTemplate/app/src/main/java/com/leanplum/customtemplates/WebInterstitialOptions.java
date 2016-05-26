// Copyright 2014, Leanplum, Inc.

package com.leanplum.customtemplates;

import android.content.Context;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.customtemplates.MessageTemplates.Args;
import com.leanplum.customtemplates.MessageTemplates.Values;

/**
 * Options used by {@link WebInterstitial}.
 * @author Atanas Dobrev
 */
public class WebInterstitialOptions {
  private String url;
  private String closeUrl;
  private boolean hasDismissButton;
  
  protected WebInterstitialOptions(ActionContext context) {
    this.setUrl(context.stringNamed(Args.URL));
    this.setHasDismissButton(context.booleanNamed(Args.HAS_DISMISS_BUTTON));
    this.setCloseUrl(context.stringNamed(Args.CLOSE_URL));
  }
  
  public String getUrl() {
    return url;
  }

  private void setUrl(String url) {
    this.url = url;
  }
  
  public boolean hasDismissButton() {
    return hasDismissButton;
  }

  private void setHasDismissButton(boolean hasDismissButton) {
    this.hasDismissButton = hasDismissButton;
  }
  
  public String getCloseUrl() {
    return closeUrl;
  }

  private void setCloseUrl(String closeUrl) {
    this.closeUrl = closeUrl;
  }

  public static ActionArgs toArgs(Context currentContext) {
    return new ActionArgs()
        .with(Args.URL,Values.DEFAULT_URL)
        .with(Args.CLOSE_URL, Values.DEFAULT_CLOSE_URL)
        .with(Args.HAS_DISMISS_BUTTON, Values.DEFAULT_HAS_DISMISS_BUTTON);
  }
}
