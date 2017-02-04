package com.kc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by kuangcheng on 17/2/4.
 */

public class TimeHelper {
    protected static SimpleDateFormat FORMATE_DATE_TIME = new SimpleDateFormat(
            "HH:mm");

    private static Date date = new Date();

    static {
        TimeZone CN_ZONE = TimeZone.getTimeZone("GMT+8");
        if (CN_ZONE != null) {
            FORMATE_DATE_TIME.setTimeZone(CN_ZONE);
        }
    }

    public static String getDateStringHm(long time) {
        synchronized (FORMATE_DATE_TIME) {
            return FORMATE_DATE_TIME.format(date);
        }
    }

    public static String getDateStringHm(Date date) {
        synchronized (FORMATE_DATE_TIME) {
            return FORMATE_DATE_TIME.format(date);
        }
    }



    public static String formatSecondsTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

//    public static String getFormatTime(long time) {
//        synchronized (date) {
//            date.setTime(time);
//            return getFormatTime(date, false);
//        }
//    }
}
