package com.briskhu.common.jgui.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * 常用的几何计算方法<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class Geometry {
    private static final Logger LOGGER = LoggerFactory.getLogger(Geometry.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 计算窗口中心点坐标
     *
     * @param frame
     * @return 坐标值为double型
     */
    public static Dimension getFrameCenter(JFrame frame) {
        Dimension result = new Dimension();

        LOGGER.debug("frame: " + frame.getWidth() + "," + frame.getHeight());
        double centerX = (frame.getWidth() + frame.getX()) / 2;
        double centerY = (frame.getHeight() + frame.getY()) / 2;
        result.setSize(centerX, centerY);

        return result;
    }

    /**
     * 计算面板当前大小的中心点坐标
     *
     * @param panel
     * @return 坐标值为double型
     */
    public static Dimension getPanelCenter(JPanel panel) {
        Dimension result = new Dimension();

        double centerX = (panel.getWidth() + panel.getX()) / 2;
        double centerY = (panel.getHeight() + panel.getY()) / 2;
        result.setSize(centerX, centerY);

        return result;
    }

    /**
     * 将target组件放置container组件的中心
     *
     * @param container
     * @param target
     */
    public static void putInCenter(JComponent container, JComponent target) {
        int x = container.getX() + (container.getWidth() - target.getWidth()) / 2;
        int y = container.getY() + (container.getHeight() - target.getHeight()) / 2;

        target.setLocation(new Point(x, y));
    }

    /**
     * 获取将target组件放置在container组件中心时的坐标数据
     * <b>注意：这个方法需要获取父组件的坐标，因此可能导致空指针异常。使用时要确保父组件已经被渲染。</b>
     * @param container
     * @param target
     * @return
     */
    public static Point getPointForPutCenter(JComponent container, JComponent target) {
        int x = container.getX() + (container.getWidth() - target.getWidth()) / 2;
        int y = container.getY() + (container.getHeight() - target.getHeight()) / 2;

        return new Point(x, y);
    }


}


