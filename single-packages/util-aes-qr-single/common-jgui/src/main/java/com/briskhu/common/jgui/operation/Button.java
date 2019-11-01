package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 按钮的基本操作工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class Button {
    private static final Logger LOGGER = LoggerFactory.getLogger(Button.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 初始化按钮
     *
     * @param btnName
     * @param btnLabel
     * @param fontSize
     * @param actionListener
     * @return
     */
    public static JButton init(String btnName, String btnLabel, int fontSize, ActionListener actionListener) {
        JButton button = new JButton(btnLabel);
        init(button, btnName, fontSize, actionListener);

        return button;
    }

    /**
     * 初始化按键
     * @param button
     * @param btnName
     * @param fontSize
     * @param actionListener
     */
    public static void init(JButton button, String btnName, int fontSize, ActionListener actionListener) {
        button.setFont(new Font(null, Font.PLAIN, fontSize));
        button.setName(btnName);
        button.addActionListener(actionListener);
    }



}


