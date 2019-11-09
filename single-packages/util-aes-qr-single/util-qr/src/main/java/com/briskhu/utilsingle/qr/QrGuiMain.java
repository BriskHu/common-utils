package com.briskhu.utilsingle.qr;

import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.other.GuiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-10-30
 **/
public class QrGuiMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(QrGuiMain.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private final String FRAME_TITLE = "二维码生成和扫描工具";
    private int frameWidth = 800;
    private int frameHeight = 900;
    private final String CREATE_QR_PAGE_TITLE = "生成二维码";
    private final String SCAN_QR_PAGE_TITLE = "扫描二维码";
    private static volatile QrGuiMain instance = null;
    private static JFrame mainWindow;
    private static GuiContext guiContext;

    /* ---------------------------------------- methods ---------------------------------------- */
    private QrGuiMain() {
        mainWindow = Frame.init(FRAME_TITLE, frameWidth, frameHeight);
        guiContext = new GuiContext();
    }

    public static QrGuiMain getInstance() {
        if (instance == null) {
            synchronized (QrGuiMain.class) {
                if (instance == null) {
                    instance = new QrGuiMain();
                }
            }
        }

        return instance;
    }

    public JFrame getMainWindow() {
        return mainWindow;
    }

    /**
     *
     */
    public void qrGuiMainPage() {
        JTabbedPane tabbedPane = new JTabbedPane();

        CreateQrPage createQrPage = new CreateQrPage();
        createQrPage.setJFrame(mainWindow);
        ScanQrPage scanQrPage = new ScanQrPage();
        scanQrPage.setJFrame(mainWindow);

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
        guiContext.saveLocale("tabIndex", tabbedPane.getSelectedIndex());
        guiContext.saveLocale("tabPanel", tabbedPane.getSelectedComponent());

        mainWindow.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                tabbedPane.setSelectedIndex((Integer) guiContext.getElement("tabIndex"));
                tabbedPane.setSelectedComponent((JPanel) guiContext.getElement("tabPanel"));
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                LOGGER.debug("[windowLostFocus] 当前选中的选项卡：index={}, element={}", tabbedPane.getSelectedIndex(), tabbedPane.getSelectedComponent());
                System.out.println(tabbedPane.getSelectedComponent());
                guiContext.saveLocale("tabIndex", tabbedPane.getSelectedIndex());
                guiContext.saveLocale("tabPanel", tabbedPane.getSelectedComponent());
            }
        });

        mainWindow.setLocationRelativeTo(null);
        Frame.refresh(mainWindow, tabbedPane);
    }


    public static void main(String[] args) {
        QrGuiMain qrGuiMain = QrGuiMain.getInstance();
        qrGuiMain.qrGuiMainPage();
    }
}


