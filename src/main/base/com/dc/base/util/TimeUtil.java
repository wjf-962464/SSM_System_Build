package com.dc.base.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
    public static String timeSubstr(String time){
        return time.substring(0,time.length()-2);
    }
    public static String timestampToString(Timestamp time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }
    public static int compareWithNow(Timestamp timestamp,String type){
        int result=0;
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime compare_time=DateTime.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp),format);
        DateTime now_time=new DateTime();
        if (type.equals("day")){
            result=Days.daysBetween(compare_time,now_time).getDays();
        }
        if (type.equals("min")){
            result= Minutes.minutesBetween(compare_time,now_time).getMinutes();
        }
        if (type.equals("sec")){
            result= Seconds.secondsBetween(compare_time,now_time).getSeconds();
        }
        return result;
    }
}
