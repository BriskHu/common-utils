package com.briskhu.utilsingle.qr;

import org.junit.Test;

import static org.junit.Assert.*;

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

    /**
     * 广东小翼管家
     * 广东 encrypted: ubZx7Ye9hAISI007tkA5PQ4bW4zDYfeuEGG1mYdtgN89Jai0H1ANHcRz675TBVXNAjb2F8ZHuLFLleifSozbvDZ9h+IlD1lJIxg4OKBR9y96opECRT2svM+NaU/z7teqftaul7YwHe7fnMn1s/8G1+Ak8SW1qoruJ4srVfwkmAg=
     * 广东 decrypted: {"boxtype": 2,"clientid": "","areano": "440000","mac": "288CB827AD74","stbid": "95100599007040700000488EEF46C1F8"}
     */
    @Test
    public void guangDongXiaoYi() throws Exception {
        String text = "ubZx7Ye9hAISI007tkA5PQ4bW4zDYfeuEGG1mYdtgN89Jai0H1ANHcRz675TBVXNAjb2F8ZHuLFLleifSozbvDZ9h+IlD1lJIxg4OKBR9y96opECRT2svM+NaU/z7teqftaul7YwHe7fnMn1s/8G1+Ak8SW1qoruJ4srVfwkmAg=";
        String resultPath = "C:\\Users\\Administrator\\Desktop\\GD-288CB827AD74-QR.jpg";

        QrCodeUtil.encode(text, resultPath);

        String decryptText = QrCodeUtil.decode("C:\\Users\\Administrator\\Desktop\\GD-288CB827AD74-QR.jpg");
        System.out.println("decryptText1: " + decryptText);

        decryptText = QrCodeUtil.decode("C:\\Users\\Administrator\\Desktop\\web.png");
        System.out.println("decryptText2: " + decryptText);
    }

    /**
     * 广东电信小翼管家App控制机顶盒-南京智家联调
     * 机顶盒信息
     * 广东 encrypted: ubZx7Ye9hAISI007tkA5PQ4bW4zDYfeuEGG1mYdtgN89Jai0H1ANHcRz675TBVXN/YWR6QKrfeoykSlT7sttpOB73Lt8DCxvjmjiGB8h+WoyA/bqE7yTN0PlG1hBxsrXyKzFJ9nnBZ8Pw4UIX0rLueAk8SW1qoruJ4srVfwkmAg=
     * 广东 decrypted: {"boxtype": 2,"clientid": "","areano": "440000","mac": "288cb827ac86","stbid": "EB100499007037300000288CB827AC86"}     * @throws Exception
     */
    @Test
    public void guangDongXiaoYi20191029() throws Exception {
        String text = "ubZx7Ye9hAISI007tkA5PQ4bW4zDYfeuEGG1mYdtgN89Jai0H1ANHcRz675TBVXN/YWR6QKrfeoykSlT7sttpOB73Lt8DCxvjmjiGB8h+WoyA/bqE7yTN0PlG1hBxsrXyKzFJ9nnBZ8Pw4UIX0rLueAk8SW1qoruJ4srVfwkmAg=";
        String resultPath = "C:\\Users\\Administrator\\Desktop\\GD-288cb827ac86-QR20191030.jpg";

        QrCodeUtil.encode(text, resultPath);

        String decryptText = QrCodeUtil.decode(resultPath);
        System.out.println("local QR decrypt: " + decryptText);

        String webQrPath = "C:\\Users\\Administrator\\Desktop\\web-20191030.png";
        decryptText = QrCodeUtil.decode(webQrPath);
        System.out.println("web QR decrypt: " + decryptText);
    }
}
