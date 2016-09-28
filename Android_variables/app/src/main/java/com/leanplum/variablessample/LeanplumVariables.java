package com.leanplum.variablessample;

import android.app.Activity;

import com.leanplum.annotations.Variable;

/**
 * Created by fede on 7/21/16.
 */
public class LeanplumVariables {

    @Variable
    public static String welcomeMessage = "welcome to leanplum";

    @Variable
    public static int intValue = 12345;
}
