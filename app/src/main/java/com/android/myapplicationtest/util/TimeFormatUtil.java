package com.android.myapplicationtest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormatUtil {



    public static String formatMillis(String date, int type) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = formatter.parse(date);
            return formatMillis(parse, type);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }


    public static String formatDuration(long duration, int type) {
//        duration /= 1000; // milliseconds into seconds
        long minute = duration / 60;
        long hour = minute / 60;
        minute %= 60;
        long second = duration % 60;
        if (type == 0) {
            if (hour != 0)
                return String.format(Locale.CHINA, "%02d'%02d'%02d''", hour, minute, second);
            else
                return String.format(Locale.CHINA, "%02d'%02d''", minute, second);
        } else if (type == 1) {
            if (hour != 0)
                return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, minute, second);
            else
                return String.format(Locale.CHINA, "%02d:%02d", minute, second);
        } else {
            if (hour != 0)
                return String.format(Locale.CHINA, "%02d'%02d'%02d''", hour, minute, second);
            else
                return String.format(Locale.CHINA, "%02d'%02d''", minute, second);
        }

    }
    public static String formatMillis(long millis, int type) {
        Date date = new Date(millis);
        return formatMillis(date, type);
    }


    //格式化时间日期
    public static String formatMillis(Date date, int type) {
        DateFormat dateFormat = null;
        switch (type) {
            case 0:
                dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case 1:
                dateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ss");
                break;
            case 2:
                dateFormat = new SimpleDateFormat("yyMMdd");
                break;
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
        }
        return dateFormat.format(date);
    }

    //两个日期的间隔 小时数
    public static  long getHourSub(long endDate, long startDate){
        long hour  = (endDate - startDate) / (60 * 60 * 1000);
        return hour;
    }
}
