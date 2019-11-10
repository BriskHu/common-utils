package com.briskhu.utilsingle.aes.algorithm;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-10
 **/
public class AESCoderTest {

    @Test
    public void encrypt() {
        String jsonString = "{\"businessType\": 2,\"account\": \"13688889999\",\"areano\": \"510000\",\"mac\": \"MAC001001001001\"," +
                "\"did\": \"03A03600091819000100000600000601\"}";
        String key = "918riKYgHH8PRrt+8IC6CQ==";
        System.out.println(key.length());
//        key = "测试中文秘钥";
        String encrypted = "";
        try {
            encrypted = new String(Base64.encodeBase64(AESCoder.encrypt(jsonString, key)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("encrypted: " + encrypted);

        String decrypted = "";
        try {
            decrypted = new String((AESCoder.decrypt(Base64.decodeBase64(encrypted), key)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("decrypted: " + decrypted);

        String wechatScanResult = "ubZx7Ye9hAISI007tkA5PQ4bW4zDYfeuEGG1mYdtgN89Jai0H1ANHcRz675TBVXNcEMtdFkac3n+7f5wzeijWqOIHkIUR5apqskHUyCKS3+Eevyg/Cen2IDoYEk8XLvM89p2T1J/sVJsW+k40nUgH+Ak8SW1qoruJ4srVfwkmAg=";
        try {
            decrypted = new String((AESCoder.decrypt(Base64.decodeBase64(wechatScanResult), key)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("wechat decrypted: " + decrypted);

    }
}