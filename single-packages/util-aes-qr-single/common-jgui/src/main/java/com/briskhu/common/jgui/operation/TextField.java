package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 文本框的基本操作工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class TextField {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextField.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 初始化文本框
     *
     * @param fieldName
     * @param columns
     * @param fontSize
     * @param hint
     * @return
     */
    public static JTextField init(String fieldName, int columns, int fontSize, String hint) {
        JTextField textField = new JTextField(columns);
        textField.setName(fieldName);
        textField.setFont(new Font(null, Font.PLAIN, fontSize));
        textField.setText(hint);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        return textField;
    }

}


