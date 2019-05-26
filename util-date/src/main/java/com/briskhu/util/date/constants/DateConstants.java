package com.briskhu.util.date.constants;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-05-26
 **/
public interface DateConstants {

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
    }

    /**
     * 时间相关常量
     */
    enum TimeConcerned{
        TIMEZONE_SHANGHAI("Asia/Shanghai"),
        ;
        
        String name = null;
        
        TimeConcerned(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
