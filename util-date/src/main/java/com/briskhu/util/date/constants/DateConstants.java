package com.briskhu.util.date.constants;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-05-26
 **/
public interface DateConstants {

    /* ---------------------------------------- date concerned ---------------------------------------- */
    /**
     * 日期字符串格式
     */
    enum DateStrFormat{
        DATE_FORMAT_MONTH("yyyy-MM"),
        DATE_FORMAT_DAY("yyyy-MM-dd"),
        DATE_FORMAT_MINITE("yyyy-MM-dd HH:mm"),
        DATE_FORMAT_SECOND("yyyy-MM-dd HH:mm:ss"),
        DATE_FORMAT_MILLI("yyyy-MM-dd HH:mm:ss.sss"),
        DATE_FORMAT_CHINESE("yyyy年MM月dd日"),
        DATE_FORMAT_CHINESE_SECONDE("yyyy年MM月dd日 HH:mm:ss"),
        DATE_FORMAT_CHINESE_WEEK_SECONDE("yyyy年MM月dd日 E HH:mm:ss"),
        ;

        String format;

        DateStrFormat(String format){
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    String DATE_FORMAT_YEAR = "yyyy";
    String DATE_FORMAT_MONTH = "yyyy-MM";
    String DATE_FORMAT_DAY = "yyyy-MM-dd";
    String DATE_FORMAT_MINITE = "yyyy-MM-dd HH:mm";
    String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_MILLI = "yyyy-MM-dd HH:mm:ss.sss";
    String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";
    String DATE_FORMAT_CHINESE_SECONDE = "yyyy年MM月dd日 HH:mm:ss";
    String DATE_FORMAT_CHINESE_WEEK_SECONDE = "yyyy年MM月dd日 E HH:mm:ss";

    /**
     * 校验年份的正则表达式。
     */
    String YEAR_RE = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})$";
    /**
     * 校验月份的正则表达式。
     */
    String MONTH_RE = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})" +
            "-(0[1-9]|1[0-2])$";
    /**
     * 校验日期的正则表达式。
     */
    String DATE_RE = "^((([0-9]{2})(0[48]|[2468][048]|[13579][26]))|((0[48]|[2468][048]|[13579][26])00)-02-29)" +
            "|([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))" +
            "|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$";


    /* ---------------------------------------- time concerned ---------------------------------------- */
    /**
     * 时间相关常量
     */
    enum TimeConcerned{
        ;
        
        String name = null;
        
        TimeConcerned(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    String TIMEZONE_SHANGHAI = "Asia/Shanghai";


}
