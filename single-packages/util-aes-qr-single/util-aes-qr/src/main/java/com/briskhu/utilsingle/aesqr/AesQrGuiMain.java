package com.briskhu.utilsingle.aesqr;

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
    private static final String DEFAULT_PATH = "C:\\Users\\Administrator\\Desktop";


    /* ---------------------------------------- methods ---------------------------------------- */
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Aes加密并生成二维码工具");
        jFrame.setSize(800, 800);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        final JTabbedPane tabbedPane = new JTabbedPane();


        tabbedPane.add("Aes 加密", createTabPanel("aesEncrypt"));
        tabbedPane.add("Aes 解密", createTabPanel("aesDecrypt"));
        tabbedPane.add("生成二维码", createTabPanel("createQr"));
        tabbedPane.add("扫描二维码", createTabPanel("scanQr"));

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                LOGGER.debug("当前选中的选项卡：" + tabbedPane.getSelectedIndex());
                LOGGER.info("当前选中的选项卡：" + tabbedPane.getSelectedIndex());
                System.out.println("当前选中的选项卡：" + tabbedPane.getSelectedIndex());
            }
        });

        tabbedPane.setSelectedIndex(0);

        jFrame.setContentPane(tabbedPane);
        jFrame.setVisible(true);
    }

    /**
     * 创建选项卡
     *
     * @param tabName
     * @return
     */
    public static JComponent createTabPanel(String tabName) {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JLabel label = new JLabel(tabName);
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label);
        return panel;
    }

}


