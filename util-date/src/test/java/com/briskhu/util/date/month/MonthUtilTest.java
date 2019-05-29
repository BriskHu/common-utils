package com.briskhu.util.date.month;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-05-29
 **/
public class MonthUtilTest {

    @Test
    public void validMonthStr() {
        String[] monthStrs = new String[]{
                "2019-05", "2019-5", "0000-01", "2019-13", "2019-00"};

        for (String s : monthStrs){
            try {
                System.out.println(MonthUtil.validMonthStr(s));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getMonthEndDay() {
    }

    @Test
    public void getMonthFirstDay() {
    }
}