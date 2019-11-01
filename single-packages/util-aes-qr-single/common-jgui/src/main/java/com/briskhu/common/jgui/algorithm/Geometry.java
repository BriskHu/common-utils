package com.briskhu.common.jgui.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
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

        System.out.println("frame: " + frame.getWidth() + "," + frame.getHeight());
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


}


