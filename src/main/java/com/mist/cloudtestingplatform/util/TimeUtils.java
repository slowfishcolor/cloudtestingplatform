package com.mist.cloudtestingplatform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Prophet on 2017/11/11.
 */
public class TimeUtils {

    public static String getDateTimeFromTimestamp(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        return simpleDateFormat.format(date);
    }
}
