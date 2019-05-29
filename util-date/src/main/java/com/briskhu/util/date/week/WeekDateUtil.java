package com.briskhu.util.date.week;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * 以周计算日期的相关工具方法<p/>
 *
 * @author Brisk Hu
 * created on 2019-05-26
 **/
public class WeekDateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeekDateUtil.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 获取入参所在周的周一
     * 约定：每周以周一开始，以周天结束。
     * @param date
     * @param calendar
     * @return
     */
    public static Date getMonday(Date date, Calendar calendar){
        LOGGER.debug("[getMonday] start ...... date = {}", date);
        Date monday = null;
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            calendar.add(Calendar.DAY_OF_WEEK, -6);
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            // do nothing
        }
        else {
            calendar.add(Calendar.DAY_OF_WEEK, 2-calendar.get(Calendar.DAY_OF_WEEK));
        }
        monday = calendar.getTime();
        LOGGER.debug("[getMonday] end. monday = {}", monday);

        return monday;
    }

    /**
     * 获取入参所在周的周天
     * 约定：每周以周一开始，以周天结束。
     * @param date
     * @param calendar
     * @return
     */
    public static Date getSunday(Date date, Calendar calendar){
        LOGGER.debug("[getSunday] start ...... date = {}", date);
        Date sunday = null;
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            // do nothing
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            calendar.add(Calendar.DAY_OF_WEEK, 6);
        }
        else {
            calendar.add(Calendar.DAY_OF_WEEK, 8-calendar.get(Calendar.DAY_OF_WEEK));
        }
        sunday = calendar.getTime();
        LOGGER.debug("[getSunday] end. sunday = {}", sunday);

        return sunday;
    }



}


