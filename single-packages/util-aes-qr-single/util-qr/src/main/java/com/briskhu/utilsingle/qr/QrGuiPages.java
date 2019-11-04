package com.briskhu.utilsingle.qr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-10-30
 **/
public class QrGuiPages {
    private static final Logger LOGGER = LoggerFactory.getLogger(QrGuiPages.class);

    /* ---------------------------------------- fileds ---------------------------------------- */



    /* ---------------------------------------- methods ---------------------------------------- */
    public void chooseOperationPage() {
        JFrame jFrame = new JFrame("二维码生成工具");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }




    public static void main(String[] args) {
        QrGuiPages qrGuiPages = new QrGuiPages();

    }
}


