package com.example.dac.app_moki.local.value;

/**
 * Created by Dac on 11/9/2017.
 */

public class ValueLocal {
    private static boolean optionView = false;
    private static String token = "";
    private static String sort="";
    private static String typeSort="";
    private static int currenTab = 1;
    private static String currentCategory = "1";

    public static String getCurrentCategory() {
        return currentCategory;
    }

    public static void setCurrentCategory(String currentCategory) {
        ValueLocal.currentCategory = currentCategory;
    }


    public static int getCurrenTab() {
        return currenTab;
    }

    public static void setCurrenTab(int currenTab) {
        ValueLocal.currenTab = currenTab;
    }


    public static String getTypeSort() {
        return typeSort;
    }

    public static void setTypeSort(String typeSort) {
        ValueLocal.typeSort = typeSort;
    }

    public static String getSort() {
        return sort;
    }

    public static void setSort(String sort) {
        ValueLocal.sort = sort;
    }


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
