// Copyright 2014, Leanplum, Inc.

package com.leanplum.customtemplates;

import static com.leanplum.customtemplates.MessageTemplates.getApplicationName;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.customtemplates.MessageTemplates.Args;
import com.leanplum.customtemplates.MessageTemplates.Values;

/**
 * Registers a Leanplum action that displays a system alert dialog.
 * @author Andrew First
 */
class Alert {
  private static final String NAME = "Alert";

  public static void register(Context currentContext) {
    Leanplum.defineAction(
        NAME,
        Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
        new ActionArgs().with(Args.TITLE, getApplicationName(currentContext))
            .with(Args.MESSAGE, Values.ALERT_MESSAGE)
            .with(Args.DISMISS_TEXT, Values.OK_TEXT)
            .withAction(Args.DISMISS_ACTION, null), new ActionCallback() {

          @Override
          public boolean onResponse(final ActionContext context) {
            LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
              @Override
              public void variablesChanged() {
                Activity activity = LeanplumActivityHelper.getCurrentActivity();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    activity);
                alertDialogBuilder
                    .setTitle(context.stringNamed(Args.TITLE))
                    .setMessage(context.stringNamed(Args.MESSAGE))
                    .setCancelable(false)
                    .setPositiveButton(context.stringNamed(Args.DISMISS_TEXT),
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                            context.runActionNamed(Args.DISMISS_ACTION);
                          }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
              }
            });
            return true;
          }
        });
  }
}
