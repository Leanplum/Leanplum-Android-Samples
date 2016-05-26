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
 * Registers a Leanplum action that displays a fullscreen interstitial.
 * @author Andrew First
 */
public class Interstitial extends BaseMessageDialog {
  private static final String NAME = "Interstitial";

  public Interstitial(Activity activity, InterstitialOptions options) {
    super(activity, true, options, null);
    this.options = options;
  }

  public static void register(Context currentContext) {
    Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
        InterstitialOptions.toArgs(currentContext),
        new ActionCallback() {
          @Override
          public boolean onResponse(final ActionContext context) {
            Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(
                new VariablesChangedCallback() {
                  @Override
                  public void variablesChanged() {
                    LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                      @Override
                      public void variablesChanged() {
                        Interstitial interstitial = new Interstitial(
                            LeanplumActivityHelper.getCurrentActivity(),
                            new InterstitialOptions(context));
                        interstitial.show();
                      }
                    });
                  }
                });
            return true;
          }
        });
  }
}
