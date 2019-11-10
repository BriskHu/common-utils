package com.briskhu.utilsingle.aesqr;

import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.other.GuiContext;
import com.briskhu.common.jgui.other.GuiDebugTools;
import com.briskhu.utilsingle.aes.uipage.AesDecryptPage;
import com.briskhu.utilsingle.aes.uipage.AesEncryptPage;
import com.briskhu.utilsingle.qr.CreateQrPage;
import com.briskhu.utilsingle.qr.ScanQrPage;
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
public class AesQrGuiMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesQrGuiMain.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private final String FRAME_TITLE = "Aes加密并生成二维码工具";
    private int frameWidth = 800;
    private int frameHeight = 900;
    public static final String AES_EN_PAGE_TITLE = "Aes加密";
    public static final String AES_DE_PAGE_TITLE = "Aes解密";
    private final String CREATE_QR_PAGE_TITLE = "生成二维码";
    private final String SCAN_QR_PAGE_TITLE = "扫描二维码";

    private static volatile AesQrGuiMain instance = null;
    private static JFrame mainWindow;
    private static GuiContext guiContext;


    private AesQrGuiMain() {
        mainWindow = Frame.init(FRAME_TITLE, frameWidth, frameHeight);
        guiContext = new GuiContext();
    }

    public static AesQrGuiMain getInstance() {
        if (instance == null) {
            synchronized (AesQrGuiMain.class) {
                if (instance == null) {
                    instance = new AesQrGuiMain();
                }
            }
        }

        return instance;
    }

    public JFrame getMainWindow() {
        return mainWindow;
    }

    /* ---------------------------------------- methods ---------------------------------------- */
    public void aesQrGuiMainPage() {
//        GuiDebugTools.setPrintBorderToggle(true);      //允许打印面板的边框

        JTabbedPane tabbedPane = new JTabbedPane();

        AesEncryptPage aesEncryptPage = new AesEncryptPage();
        aesEncryptPage.setJFrame(mainWindow);
        AesDecryptPage aesDecryptPage = new AesDecryptPage();
        aesDecryptPage.setJFrame(mainWindow);

        CreateQrPage createQrPage = new CreateQrPage();
        createQrPage.setJFrame(mainWindow);
        ScanQrPage scanQrPage = new ScanQrPage();
        scanQrPage.setJFrame(mainWindow);

        tabbedPane.add(AES_EN_PAGE_TITLE, aesEncryptPage.createAesEncryptPagePanel());
        tabbedPane.add(AES_DE_PAGE_TITLE, aesDecryptPage.createAesDecryptPagePanel());
        tabbedPane.add(CREATE_QR_PAGE_TITLE, createQrPage.createQrPagePanel());
        tabbedPane.add(SCAN_QR_PAGE_TITLE, scanQrPage.scanQrPagePanel());

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectTabelIndex = tabbedPane.getSelectedIndex();
                LOGGER.debug("[stateChanged] 当前选中的选项卡：{}", selectTabelIndex);
                tabbedPane.setSelectedIndex(selectTabelIndex);
                Frame.refresh(mainWindow, tabbedPane);
            }
        });
        tabbedPane.setSelectedIndex(0);

        mainWindow.setLocationRelativeTo(null);
        Frame.refresh(mainWindow, tabbedPane);
    }

    public static void main(String[] args) {
        AesQrGuiMain aesGuiMain = AesQrGuiMain.getInstance();
        aesGuiMain.aesQrGuiMainPage();
    }

}


