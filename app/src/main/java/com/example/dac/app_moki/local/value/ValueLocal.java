package com.example.dac.app_moki.local.value;

/**
 * Created by Dac on 11/9/2017.
 */

public class ValueLocal {
    private static boolean optionView = false;
    private static String token = "";

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ValueLocal.token = token;
    }

    public static boolean getOptionView() {
        return optionView;
    }

    public static void setOptionView(boolean optionView) {
        ValueLocal.optionView = optionView;
    }
}
