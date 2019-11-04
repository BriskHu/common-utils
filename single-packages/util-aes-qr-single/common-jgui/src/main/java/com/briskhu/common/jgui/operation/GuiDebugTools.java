package com.briskhu.common.jgui.operation;

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

}


