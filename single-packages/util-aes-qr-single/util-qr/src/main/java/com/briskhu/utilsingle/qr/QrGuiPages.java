package com.briskhu.utilsingle.qr;

import com.briskhu.common.jgui.operation.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-10-30
 **/
public class QrGuiPages {
    private static final Logger LOGGER = LoggerFactory.getLogger(QrGuiPages.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private final String FRAME_TITLE = "二维码生成和扫描工具";
    private int frameWidth = 800;
    private int frameHeight = 830;
    private final String CREATE_QR_PAGE_TITLE = "生成二维码";
    private final String SCAN_QR_PAGE_TITLE = "扫描二维码";


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     *
     */
    public void qrGuiMainPage(){
        JFrame mainWindow = Frame.init(FRAME_TITLE, frameWidth, frameHeight);
        JTabbedPane tabbedPane = new JTabbedPane();

        CreateQrPage createQrPage = new CreateQrPage();
        ScanQrPage scanQrPage = new ScanQrPage();

        tabbedPane.add(CREATE_QR_PAGE_TITLE, createQrPage.createQrPagePanel());
        tabbedPane.add(SCAN_QR_PAGE_TITLE, scanQrPage.scanQrPagePanel());

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectTabelIndex = tabbedPane.getSelectedIndex();
                LOGGER.debug("[qrGuiMainPage] 当前选中的选项卡：{}", selectTabelIndex);
                tabbedPane.setSelectedIndex(selectTabelIndex);
                Frame.refresh(mainWindow, tabbedPane);
            }
        });
        tabbedPane.setSelectedIndex(0);
        mainWindow.setLocationRelativeTo(null);
        Frame.refresh(mainWindow, tabbedPane);
    }


    public static void main(String[] args) {
        QrGuiPages qrGuiPages = new QrGuiPages();
        qrGuiPages.qrGuiMainPage();
    }
}


