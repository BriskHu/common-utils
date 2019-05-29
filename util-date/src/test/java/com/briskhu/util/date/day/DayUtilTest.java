package com.briskhu.util.date.day;

import com.briskhu.util.date.constants.DateConstants;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-05-29
 **/
public class DayUtilTest {

    @Test
    public void validDayStr() {
        String[] dateStrs = new String[]{
                "2019-05-29", "2019-00-00", "2019-13-01", "2019-01-33",
                "2019-02-29", "2018-02-29", "2012-02-29", "0000-01-01"};
        for (String s : dateStrs){
            try {
                System.out.println(s+" " + DayUtil.validDayStr(s));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void getDayBegin() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DateConstants.TIMEZONE_SHANGHAI));
        try {
            Date[] dates = new Date[]{
                    new Date(), DayUtil.validDayStr("2018-02-28"),
                    DayUtil.validDayStr("2019-01-05"), DayUtil.validDayStr("2019-05-29")};
            for (Date date : dates){
                System.out.println(date+" " + DayUtil.getDayBegin(date, calendar));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDayEnd() {
    }
}