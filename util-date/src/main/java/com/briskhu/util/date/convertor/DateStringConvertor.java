package com.briskhu.util.date.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期和字符串的相互转化<p/>
 *
 * @author Brisk Hu
 * created on 2019-05-26
 **/
public class DateStringConvertor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateStringConvertor.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private static final String[] WEEK_ARR = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MINITE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MILLI = "yyyy-MM-dd HH:mm:ss.sss";
    public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_CHINESE_SECONDE = "yyyy年MM月dd日 HH:mm:ss";
    public static final String DATE_FORMAT_CHINESE_WEEK_SECONDE = "yyyy年MM月dd日 E HH:mm:ss";
    public static final long DAY_MS = 86400000L;


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 将指定格式的字符串转换为日期
     * @param sDate
     * @param format
     * @return
     */
    public static Date strToDate(String sDate, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            return simpleDateFormat.parse(sDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }


}


