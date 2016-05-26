package com.leanplum.customtemplates;

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

import static com.leanplum.customtemplates.MessageTemplates.getApplicationName;

// Creating a new class for the 3-buttons Confirm Message
// Class name will be the name displayed in the Message dropdown on the Dashboard

// In this case we basically got the code from the Confirm class and added code for the third 'Maybe' button

public class Confirm3Buttons {

    private static final String NAME = "3-buttons Confirm";

    public static void register(Context currentContext) {
        Leanplum.defineAction(
                NAME,
                Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
                new ActionArgs().with(MessageTemplates.Args.TITLE, getApplicationName(currentContext))
                        .with(MessageTemplates.Args.MESSAGE, MessageTemplates.Values.CONFIRM_MESSAGE)
                        .with(MessageTemplates.Args.ACCEPT_TEXT, MessageTemplates.Values.YES_TEXT)
                        .with(MessageTemplates.Args.CANCEL_TEXT, MessageTemplates.Values.NO_TEXT)
                        // #### example: adding Text and Values for the Maybe button options
                        .with(MessageTemplates.Args.MAYBE_TEXT, MessageTemplates.Values.MAYBE_TEXT)
                        .withAction(MessageTemplates.Args.ACCEPT_ACTION, null)
                        .withAction(MessageTemplates.Args.CANCEL_ACTION, null)
                        // #### example: adding the Action for the Maybe button
                        .withAction(MessageTemplates.Args.MAYBE_ACTION, null), new ActionCallback() {

                    @Override
                    public boolean onResponse(final ActionContext context) {
                        LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                            @Override
                            public void variablesChanged() {
                                Activity activity = LeanplumActivityHelper.getCurrentActivity();
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        activity);
                                alertDialogBuilder
                                        .setTitle(context.stringNamed(MessageTemplates.Args.TITLE))
                                        .setMessage(context.stringNamed(MessageTemplates.Args.MESSAGE))
                                        .setCancelable(false)
                                        .setPositiveButton(context.stringNamed(MessageTemplates.Args.ACCEPT_TEXT),
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        context.runTrackedActionNamed(MessageTemplates.Args.ACCEPT_ACTION);
                                                    }
                                                })
                                        .setNegativeButton(context.stringNamed(MessageTemplates.Args.CANCEL_TEXT),
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        context.runActionNamed(MessageTemplates.Args.CANCEL_ACTION);
                                                    }
                                                })

                                        // #### example: adding the Maybe button to the Alert dialog
                                        .setNeutralButton(context.stringNamed(MessageTemplates.Args.MAYBE_TEXT),
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        context.runActionNamed(MessageTemplates.Args.MAYBE_ACTION);
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
