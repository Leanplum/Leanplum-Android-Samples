// Copyright 2014, Leanplum, Inc.

package com.leanplum.customtemplates;

import android.app.Activity;
import android.content.Context;

import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Registers a Leanplum action that displays a fullscreen Web Interstitial.
 *
 * @author Atanas Dobrev
 */
public class WebInterstitial extends BaseMessageDialog {
  private static final String NAME = "Web Interstitial";

  public WebInterstitial(Activity activity, WebInterstitialOptions options) {
    super(activity, true, null, options);
    this.webOptions = options;
  }

  public static void register(Context currentContext) {
    Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
        WebInterstitialOptions.toArgs(currentContext), new ActionCallback() {
          @Override
          public boolean onResponse(final ActionContext context) {
            LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
              @Override
              public void variablesChanged() {
                WebInterstitial webInterstitial = new WebInterstitial(
                    LeanplumActivityHelper.getCurrentActivity(),
                    new WebInterstitialOptions(context));
                webInterstitial.show();
              }
            });
            return true;
          }
        });
  }
}
