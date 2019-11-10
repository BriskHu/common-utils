package com.briskhu.utilsingle.aes;

import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.other.GuiContext;
import com.briskhu.utilsingle.aes.uipage.AesDecryptPage;
import com.briskhu.utilsingle.aes.uipage.AesEncryptPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Aes加密解密工作主界面<p/>
 *
 * @author Brisk Hu
 * created on 2019-10-31
 **/
public class AesGuiMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesGuiMain.class);

    private final String FRAME_TITLE = "Aes加密、解密工具";
    private int frameWidth = 800;
    private int frameHeight = 900;
    public static final String AES_EN_PAGE_TITLE = "Aes加密";
    public static final String AES_DE_PAGE_TITLE = "Aes解密";

    private static volatile AesGuiMain instance = null;
    private static JFrame mainWindow;
    private static GuiContext guiContext;


    private AesGuiMain(){
        mainWindow = Frame.init(FRAME_TITLE, frameWidth, frameHeight);
        guiContext = new GuiContext();
    }

    public static AesGuiMain getInstance() {
        if (instance == null){
            synchronized (AesGuiMain.class){
                if (instance == null){
                    instance = new AesGuiMain();
                }
            }
        }

        return instance;
    }

    public JFrame getMainWindow() {
        return mainWindow;
    }

    public void aesGuiMainPage(){
        JTabbedPane tabbedPane = new JTabbedPane();
        AesEncryptPage aesEncryptPage = new AesEncryptPage();
        aesEncryptPage.setJFrame(mainWindow);
        AesDecryptPage aesDecryptPage = new AesDecryptPage();
        aesDecryptPage.setJFrame(mainWindow);

        tabbedPane.add(AES_EN_PAGE_TITLE, aesEncryptPage.createAesEncryptPagePanel());
        tabbedPane.add(AES_DE_PAGE_TITLE, aesDecryptPage.createAesDecryptPagePanel());

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
       AesGuiMain aesGuiMain = AesGuiMain.getInstance();
       aesGuiMain.aesGuiMainPage();
    }
}
