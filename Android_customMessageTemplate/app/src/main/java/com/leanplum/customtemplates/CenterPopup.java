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
 * Registers a Leanplum action that displays a custom center popup dialog.
 * @author Andrew First
 */
public class CenterPopup extends BaseMessageDialog {
  private static final String NAME = "Center Popup";

  public CenterPopup(Activity activity, CenterPopupOptions options) {
    super(activity, false, options, null);
    this.options = options;
  }

  public static void register(Context currentContext) {
    Leanplum.defineAction(NAME, Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
        CenterPopupOptions.toArgs(currentContext), new ActionCallback() {
          @Override
          public boolean onResponse(final ActionContext context) {
            Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(
                new VariablesChangedCallback() {
              @Override
              public void variablesChanged() {
                LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                  @Override
                  public void variablesChanged() {
                    CenterPopup popup = new CenterPopup(
                        LeanplumActivityHelper.getCurrentActivity(),
                        new CenterPopupOptions(context));
                    popup.show();
                  }
                });
              }
            });
            return true;
          }
        });
  }
}
