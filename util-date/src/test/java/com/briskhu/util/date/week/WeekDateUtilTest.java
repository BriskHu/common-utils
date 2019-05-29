package com.briskhu.util.date.week;

import com.briskhu.util.date.constants.DateConstants;
import com.briskhu.util.date.convertor.DateStringConvertor;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-05-26
 **/
public class WeekDateUtilTest {


    @Test
    public void getMonday() {
        System.out.println("Test monday...");

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DateConstants.TIMEZONE_SHANGHAI));

        for (int i=0; i<10; i++){
            date = DateStringConvertor.strToDate("2019-01-0"+i, DateStringConvertor.DATE_FORMAT_DAY);
            Date monday = WeekDateUtil.getMonday(date, calendar);
            System.out.println(monday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
        }

        date = DateStringConvertor.strToDate("2018-12-29", DateStringConvertor.DATE_FORMAT_DAY);
        Date monday = WeekDateUtil.getMonday(date, calendar);
        System.out.println(monday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
        date = DateStringConvertor.strToDate("2018-12-30", DateStringConvertor.DATE_FORMAT_DAY);
        monday = WeekDateUtil.getMonday(date, calendar);
        System.out.println(monday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
        date = DateStringConvertor.strToDate("2018-12-31", DateStringConvertor.DATE_FORMAT_DAY);
        monday = WeekDateUtil.getMonday(date, calendar);
        System.out.println(monday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
    }

    @Test
    public void getSunday(){
        System.out.println("Test sunday...");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DateConstants.TIMEZONE_SHANGHAI));

        for (int i=0; i<10; i++){
            date = DateStringConvertor.strToDate("2019-01-0"+i, DateStringConvertor.DATE_FORMAT_DAY);
            Date sunday = WeekDateUtil.getSunday(date, calendar);
            System.out.println(sunday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
        }
        date = DateStringConvertor.strToDate("2018-12-29", DateStringConvertor.DATE_FORMAT_DAY);
        Date sunday = WeekDateUtil.getSunday(date, calendar);
        System.out.println(sunday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
        date = DateStringConvertor.strToDate("2018-12-30", DateStringConvertor.DATE_FORMAT_DAY);
        sunday = WeekDateUtil.getSunday(date, calendar);
        System.out.println(sunday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));
        date = DateStringConvertor.strToDate("2018-12-31", DateStringConvertor.DATE_FORMAT_DAY);
        sunday = WeekDateUtil.getSunday(date, calendar);
        System.out.println(sunday +" "+ calendar.getWeekYear() + " " + calendar.get(Calendar.WEEK_OF_YEAR));

    }


}
