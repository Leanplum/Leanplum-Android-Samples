package com.android_playground;

import com.leanplum.Var;
import com.leanplum.annotations.Variable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fede on 12/7/16.
 */

public class GlobalVariables {

    @Variable
    public static String welcomeString = "Welcome to Leanplum";

    @Variable public static Map<String, Object> powerup = new HashMap<String, Object>() {
        {
            put("name", "Turbo Boost");
        }
    };

    static Var<String> mario1 = Var.defineAsset("Mario1", "Mario.png");

}
