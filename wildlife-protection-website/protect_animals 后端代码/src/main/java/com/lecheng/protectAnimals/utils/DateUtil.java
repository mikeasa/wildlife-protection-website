package com.lecheng.protectAnimals.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String format="yyyy-MM-dd";
    public static SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);

    public static Date stringToFormatDate(String dateString) throws ParseException {
        return  simpleDateFormat.parse(dateString);
    }
    public static String simpleDateFormatToString(Date date) throws ParseException {
        return  simpleDateFormat.format(date);
    }
    public static Date getCurrentTime(){
        return new Date();
    }

}
