package com.briskhu.common.jgui.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Gui开发的常用工具方法<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-03
 **/
public class GuiDebugTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuiDebugTools.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private static boolean printBorderToggle = false;


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 打印组件的边框
     * 用来展示组件的布局边界
     * @param borderColor
     * @param components
     */
    public static void printBorder(Color borderColor, JComponent... components) {
        for (JComponent com : components){
            com.setBorder(BorderFactory.createLineBorder(borderColor));
        }
    }

    /**
     * 通过开关控制边界打印
     * 这里的开关是本类的 printBorderToggle 变量。该变量可以通过本类的 setPrintBorderToggle() 方法来进行设置。
     * <b>注意：</b>该setPrintBorderToggle方法设置printBorderToggle变量的值后，对本类的所有静态方法都生效。
     * @param borderColor
     * @param components
     */
    public static void printBorderByToggle(Color borderColor, JComponent... components) {
        if (printBorderToggle){
            for (JComponent com : components){
                com.setBorder(BorderFactory.createLineBorder(borderColor));
            }
        }
    }


    public static boolean isPrintBorderToggle() {
        return printBorderToggle;
    }

    public static void setPrintBorderToggle(boolean printBorderToggle) {
        GuiDebugTools.printBorderToggle = printBorderToggle;
    }


}


