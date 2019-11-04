package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 面板的基本操作工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class Panel {
    private static final Logger LOGGER = LoggerFactory.getLogger(Panel.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 基于绝对布局初始化面板
     * @param panelName
     * @param width
     * @param height
     * @return
     */
    public static JPanel init(String panelName, int width, int height) {
        JPanel result = new JPanel();
        result.setName(panelName);
        result.setPreferredSize(new Dimension(width, height));
//        result.setSize(width, height);
        return result;
    }


    /**
     * 基于流布局初始化面板
     * 默认为左对齐方式
     * @param panelName
     * @param width
     * @param height
     * @return
     */
    public static JPanel initByFlow(String panelName, int width, int height) {
        JPanel result = init(panelName, width, height);
        result.setLayout(new FlowLayout(FlowLayout.LEFT));

        return result;
    }


    /**
     * 基于流布局初始化面板并添加Box对象
     * @param panelName
     * @param width
     * @param height
     * @param box Box对象
     * @return
     */
    public static JPanel initForBox(String panelName, int width, int height, Box box) {
        JPanel result = initByFlow(panelName, width, height);

        result.add(box);
        return result;
    }

    /**
     * 将组件添加到面板中
     * @param panel
     * @param components
     */
    public static void add(JPanel panel, JComponent... components){
        for (int i=0; i<components.length; i++){
            panel.add(components[i]);
        }
    }


    /**
     * 重新加载面板内的元素
     * @param rootPanel
     * @param childrens
     */
    public static void reloadChildren(JPanel rootPanel, JPanel... childrens) {
        for (int i = 0; i < childrens.length; i++) {
            rootPanel.remove(childrens[i]);
            rootPanel.add(childrens[i]);
        }
    }



}


