package com.leanplum.android_splashscreen_callbacks;

import com.leanplum.Var;
import com.leanplum.annotations.Variable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fede on 8/23/16.
 */
public class GlobalVariables {

    // Defining Variables using Annotations

    // String_Welcome1 and String_Welcome2 are going to be displayed on Screen - see MainActivity class
    @Variable
    public static String String_Welcome1 = "Welcome to Leanplum!";
    @Variable
    public static String String_Welcome2 = "You can change those text strings overriding 'String_Welcome1' and 'String_Welcome2' values on the Dashboard variables";

    @Variable
    public static Map<String, Object> powerup = new HashMap<String, Object>() {
        {
            put("name", "Turbo Boost");
            put("price", 150);
            put("speedMultiplier", 1.5);
            put("timeout", 15);
            put("slots", Arrays.asList(1, 2, 3));
        }
    };

    static Var<String> mario1 = Var.defineAsset("Mario1", "Mario.png");
}
