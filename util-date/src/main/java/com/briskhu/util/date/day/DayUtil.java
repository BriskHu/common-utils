package com.briskhu.util.date.day;

import com.briskhu.util.date.constants.DateConstants;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 天的处理<p/>
 *
 * @author Brisk Hu
 * created on 2019-05-29
 **/
public class DayUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DayUtil.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 检查一个字符串是否是yyyy-MM-dd格式的日期，是则将其转换为一个Date，否则抛出异常。
     * @param dayStr
     * @return
     * @throws ParseException
     */
    public static Date validDayStr(String dayStr) throws ParseException {
        return DayUtil.validDayStr(dayStr, DateConstants.DATE_FORMAT_DAY);
    }

    /**
     * 根据指定格式将字符串转换为对应的Date对象。
     * 本方法目前支持对yyyy-MM-dd格式的日期进行校验。
     * TODO-Brisk 后续完善。
     * @param dayStr
     * @param dayFormat
     * @return
     * @throws ParseException
     */
    @Deprecated
    public static Date validDayStr(String dayStr, String dayFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dayFormat);
        try {
            if (dayStr.matches(DateConstants.DATE_RE)){
                Date day = format.parse(dayStr);
                LOGGER.debug("[validDayStr] day = {}", day);
                return day;
            }
            else {
                throw new ParseException(dayStr+"的格式不满足"+dayFormat+"格式。", 0);
            }
        } catch (ParseException e) {
            LOGGER.error("[validDayStr] {}的格式不满足{}格式。", dayStr, dayFormat);
            throw e;
        }
    }

    /**
     * 获取一天的开始
     * 即获得当天的 0:00:00.000时刻
     * @param day
     * @param calendar
     * @return
     */
    public static Date getDayBegin(Date day, Calendar calendar){
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的结束
     * 即获得当天的 23:59:59.999时刻
     * @param day
     * @param calendar
     * @return
     */
    public static Date getDayEnd(Date day, Calendar calendar){
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

}


