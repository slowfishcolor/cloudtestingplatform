package com.mist.cloudtestingplatform.util;

/**
 * Created by Prophet on 2017/11/15.
 */
public class StringUtils {

    public static boolean isNotEmpty(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

}
