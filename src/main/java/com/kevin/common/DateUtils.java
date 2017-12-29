package com.kevin.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by kevin on 2017/11/29.
 */
public class DateUtils {

    public static int getCurrentHours(){
        Calendar calendar  = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 返回零点的日期对象
     *
     * @param date 日期 @see Date
     * @return 零点的日期对象 ，如果<code>date</code>为<code>null</code>，返回 <code>null</code>
     */
    public static Date getStartOfDate(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取上周的星期一
     *
     * @return 上周的星期一
     */
    public static Date getPreviousMonday() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        Date date;
        if (dayOfWeek == 1) {
            date = addDays(cd.getTime(), -7);
        } else {
            date = addDays(cd.getTime(), -6 - dayOfWeek);
        }
        return getStartOfDate(date);
    }

    /**
     * 获取一个月之前的星期一
     *
     * @return 一个月之前的星期一
     */
    public static Date getMondayBefore4Week() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        Date date;
        if (dayOfWeek == 1) {
            date = addDays(cd.getTime(), -28);
        } else {
            date = addDays(cd.getTime(), -27 - dayOfWeek);
        }
        return getStartOfDate(date);
    }

    /**
     * 获取本周的星期一
     *
     * @return the day of current monday
     */
    public static Date getCurrentMonday() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        Date date;
        if (dayOfWeek == 1) {
            date = cd.getTime();
        } else {
            date = addDays(cd.getTime(), 1 - dayOfWeek);
        }
        return getStartOfDate(date);
    }

    /**
     * 返回给定日期时间所在月份的最后一天
     *
     * @param date 给定的日期对象 @see Date
     * @return 给定日期时间所在月份的最后一天
     */
    public static Date getEndOfMonth(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DATE, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 返回给定日期时间所在月份的第一天
     *
     * @param date 给定的日期对象 @see Date
     * @return 给定日期时间所在月份的第一天
     */
    public static Date getFirstOfMonth(final Date date) {
        Date lastMonth = addMonths(date, -1);

        lastMonth = getEndOfMonth(lastMonth);
        return addDays(lastMonth, 1);
    }


    /**
     * 给指定日期增加月份数
     *
     * @param date 指定日期 @see Date
     * @param months 增加的月份数
     * @return 增加月份后的日期
     */
    public static Date addMonths(Date date, int months) {
        if (months == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 给指定日期增加天数
     *
     * @param date 指定日期 @see Date
     * @param days 增加的天数
     * @return 增加天数后的日期
     */
    public static Date addDays(final Date date, int days) {
        if (days == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return cal.getTime();
    }

    /**
     * 给指定日期增加分钟
     *
     * @param date 指定日期 @see Date
     * @param mins 增加的分钟
     * @return 增加分钟后的日期
     */
    public static Date addMins(final Date date, int mins) {
        if (mins == 0) {
            return date;
        }

        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);

        return cal.getTime();
    }

    public static Date getYearBegin(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);

        return cal.getTime();
    }

    public static Date getYearEnd(final Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 11);

        return getEndOfMonth(cal.getTime());
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getCurrentHours());
    }
}
