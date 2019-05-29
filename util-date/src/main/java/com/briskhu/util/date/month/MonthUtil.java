package com.briskhu.util.date.month;

import com.briskhu.util.date.constants.DateConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 月的处理<p/>
 *
 * @author Brisk Hu
 * created on 2019-05-29
 **/
public class MonthUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonthUtil.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 月份参数校验
     * @param monthStr
     * @return 如果参数通过校验，则返回入参对应的Date。Date为这个月的第一天的0:00:00.000。
     * 如果校验不通过，则抛出异常。
     * @throws ParseException
     */
    public static Date validMonthStr(String monthStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DateConstants.DATE_FORMAT_MONTH);
        try {
            if (monthStr.matches(DateConstants.MONTH_RE)){
                Date month = format.parse(monthStr);
                LOGGER.debug("[validMonthStr] month = {}", month);
                return month;
            }
            else {
                throw new ParseException(monthStr+"的格式不满足yyyy-MM格式。", 0);
            }
        } catch (ParseException e) {
            LOGGER.error("[validMonthStr] {}的格式不满足yyyy-MM格式。", monthStr);
            throw e;
        }
    }

    /**
     * 将日期转换为yyyy-MM格式的字符串
     * @param date
     * @return
     */
    public static String dateToMonthString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT_MONTH);
        return dateFormat.format(date);
    }

    /**
     * 获取指定日期所在月的最后一天
     * @param date
     * @return
     */
    public static Date getMonthEndDay(Date date, Calendar calendar){
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDay = calendar.getTime();
        LOGGER.debug("[getMonthEndDay] end: endDay = {}", endDay);
        return endDay;
    }

    /**
     * 获取指定日期所在月的第一天
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date, Calendar calendar){
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date endDay = calendar.getTime();
        LOGGER.debug("[getMonthFirstDay] end: endDay = {}", endDay);
        return endDay;
    }


}


