package com.briskhu.utilsingle.aes.uipage;

import com.briskhu.utilsingle.aes.AesGuiMain;
import org.junit.Assert;
import org.junit.Test;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-12
 **/
public class AesEncryptPageTest {

    @Test
    public void timestamp() {
        System.out.println("currentTimeMillis = " + System.currentTimeMillis());
        System.out.println("Date = " + new Date());
    }

    @Test
    public void timestampToDate() {
        String timeFieldStr = "1575712071799";
        System.out.println("[getDateBtn-actionPerformed] timeFieldStr = " + timeFieldStr);
        if (!timeFieldStr.matches("^\\d+$")) {
            System.out.println("[getDateBtn-actionPerformed] 时间戳格式错误");
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        Date date = Date.from(Instant.ofEpochMilli(Long.parseLong(timeFieldStr)));
        String dateStr = simpleDateFormat.format(date);
        System.out.println(dateStr);
        Assert.assertEquals(dateStr, "2019-12-07 17:47:51.051");

        timeFieldStr = "1575712071a99";
        System.out.println("[getDateBtn-actionPerformed] timeFieldStr = " + timeFieldStr);
        Assert.assertFalse(timeFieldStr.matches("^\\d+$"));
        if (!timeFieldStr.matches("^\\d+$")) {
            System.out.println("[getDateBtn-actionPerformed] 时间戳格式错误");
            return;
        }
    }

}