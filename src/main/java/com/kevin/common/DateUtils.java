package com.kevin.common;

import java.util.Calendar;

/**
 * Created by kevin on 2017/11/29.
 */
public class DateUtils {

    public static int getCurrentHours(){
        Calendar calendar  = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static void main(String[] args) {
        System.out.println(DateUtils.getCurrentHours());
    }
}
