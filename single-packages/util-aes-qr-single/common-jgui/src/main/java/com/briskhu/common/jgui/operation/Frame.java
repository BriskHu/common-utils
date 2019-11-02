package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 * 窗口的基本操作工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class Frame {
    private static final Logger LOGGER = LoggerFactory.getLogger(Frame.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 初始化窗口
     *
     * @param frameTitle 窗口标题
     * @param frameName
     * @param width
     * @param height
     * @return
     */
    public static JFrame init(String frameTitle, String frameName, int width, int height) {
        JFrame frame = new JFrame(frameTitle);
        frame.setName(frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        return frame;
    }

    /**
     * 初始化窗口
     * 不指定Frame的name属性时，name属性的值不是null而是一个为“frameX”的字符串，X是一个按照frame的创建顺序增加的数字。
     * <b>除Frame的name的默认值不为null外，其他组件的name值默认都是null。</b>
     * @param frameTitle 窗口标题
     * @param width
     * @param height
     * @return
     */
    public static JFrame init(String frameTitle, int width, int height) {
        JFrame frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        return frame;
    }

    /**
     * 刷新窗口
     * @param frame
     */
    public static void refresh(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * 刷新窗口
     * @param frame
     * @param panel
     */
    public static void refresh(JFrame frame, JPanel panel) {
//        frame.setVisible(false);
        frame.setContentPane(panel);
        refresh(frame);
    }

    public static void setWindowState(JFrame frame){
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                LOGGER.debug("old={}, new={}", e.getOldState(), e.getNewState());

                if (e.getNewState()==1 ||  e.getNewState()==7){
                    LOGGER.debug("min: old={}, new={}", e.getOldState(), e.getNewState());
                }
                else if (e.getNewState()==0){
                    LOGGER.debug("restore: old={}, new={}", e.getOldState(), e.getNewState());
                    frame.setVisible(false);
                    frame.setPreferredSize(new Dimension(800, 800));
                    frame.pack();
                    refresh(frame);
                }
                else if(e.getNewState()==6){
                    LOGGER.debug("restore: old={}, new={}", e.getOldState(), e.getNewState());
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                LOGGER.debug("[windowActivated] old={}, new={}", e.getOldState(), e.getNewState());
            }

            @Override
            public void windowStateChanged(WindowEvent e) {
                LOGGER.debug("[windowStateChanged] old={}, new={}", e.getOldState(), e.getNewState());

                if (e.getNewState()==1 ||  e.getNewState()==7){
                    LOGGER.debug("min: old={}, new={}", e.getOldState(), e.getNewState());
                }
                else if (e.getNewState()==0){
                    LOGGER.debug("restore: old={}, new={}", e.getOldState(), e.getNewState());
                    frame.setVisible(false);
                    frame.setPreferredSize(new Dimension(800, 800));
                    frame.pack();
                    refresh(frame);
                }
                else if(e.getNewState()==6){
                    LOGGER.debug("restore: old={}, new={}", e.getOldState(), e.getNewState());
                }
            }

        });
    }

}


