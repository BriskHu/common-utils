package com.briskhu.encode_decode.qrcode;


import org.junit.Test;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-09-02
 **/
public class QrCodeUtilTest {

    @Test
    public void encode() throws Exception {
        String text = "ubZx7Ye9hAISI007tkA5PQ4bW4zDYfeuEGG1mYdtgN89Jai0H1ANHcRz675TBVXNcEMtdFkac3n+7f5wzeijWqOIHkIUR5apqskHUyCKS3+Eevyg/Cen2IDoYEk8XLvM89p2T1J/sVJsW+k40nUgH+Ak8SW1qoruJ4srVfwkmAg=";
        String logoPath = "";
        String resultPath = "C:\\Users\\Administrator\\Desktop\\qrTest.jpg";

        QrCodeUtil.encode(text, resultPath);

        String decryptText = QrCodeUtil.decode("C:\\Users\\Administrator\\Desktop\\qrTest.jpg");
        System.out.println("decryptText1: " + decryptText);

        decryptText = QrCodeUtil.decode("C:\\Users\\Administrator\\Desktop\\web.png");
        System.out.println("decryptText2: " + decryptText);
    }




}
