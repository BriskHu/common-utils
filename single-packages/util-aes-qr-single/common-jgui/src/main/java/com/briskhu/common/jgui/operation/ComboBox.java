package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 * 单选下拉列表的基本工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class ComboBox {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComboBox.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 初始化字符型下拉列表
     *
     * @param spinnerName
     * @param fontSize
     * @param size
     * @param items
     * @param itemListener
     * @return
     */
    public static JComboBox<String> initForString(String spinnerName, int fontSize, Dimension size, String[] items, ItemListener itemListener) {
        JComboBox<String> result = initForString(spinnerName, fontSize, items, itemListener);
        result.setPreferredSize(size);

        return result;
    }

    /**
     * 初始化字符型下拉列表
     *
     * @param spinnerName
     * @param fontSize
     * @param items
     * @param itemListener
     * @return
     */
    public static JComboBox<String> initForString(String spinnerName, int fontSize, String[] items, ItemListener itemListener) {
        JComboBox<String> result = initForString(spinnerName, fontSize, items);
        result.addItemListener(itemListener);

        return result;
    }

    /**
     * 初始化字符型下拉列表
     *
     * @param spinnerName
     * @param fontSize
     * @param items
     * @return
     */
    public static JComboBox<String> initForString(String spinnerName, int fontSize, String[] items) {
        JComboBox<String> result = new JComboBox<String>(items);
        result.setName(spinnerName);
        result.setFont(new Font(null, Font.PLAIN, fontSize));
        result.setSelectedIndex(0);

        return result;
    }


}


