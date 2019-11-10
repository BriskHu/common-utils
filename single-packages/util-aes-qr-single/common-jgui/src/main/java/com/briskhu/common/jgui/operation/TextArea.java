package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * TextArea常用工具方法<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-03
 **/
public class TextArea {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextArea.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 初始化文本区域
     * 不指定Label的name属性时，name属性的值为“null”，这点与Frame的特点不一样。
     *
     * @param textAreaStr
     * @param fontSize
     * @return
     */
    public static JTextArea init(String textAreaStr, int fontSize) {
        return init(textAreaStr, null, fontSize);
    }

    /**
     * 初始化文本区域
     *
     * @param textAreaStr
     * @param textAreaName
     * @param fontSize
     * @return
     */
    public static JTextArea init(String textAreaStr, String textAreaName, int fontSize) {
        JTextArea textArea = new JTextArea(textAreaStr);
        textArea.setName(textAreaName);
        init(textArea, fontSize);

        return textArea;
    }

    public static void init(JTextArea textArea, int fontSize) {
        textArea.setFont(new Font(null, Font.PLAIN, fontSize));
        textArea.setLineWrap(true);         //激活自动换行功能
        textArea.setWrapStyleWord(true);     // 激活断行不断字功能
    }

    /**
     * 初始化为带垂直滚动条的文本域
     *
     * @param textAreaStr
     * @param textAreaName
     * @param fontSize
     * @param scrollSize
     * @return
     */
    public static JScrollPane initWithScrollbar(String textAreaStr, String textAreaName, int fontSize, Dimension scrollSize) {
        JTextArea textArea = init(textAreaStr, textAreaName, fontSize);
        JScrollPane scrollPanel = putIntoScrollbar(textArea, null);

        return scrollPanel;
    }

    /**
     * @param textArea
     * @return
     */
    public static JScrollPane putIntoScrollbar(JTextArea textArea, Dimension size) {
        JScrollPane scrollPanel = new JScrollPane(textArea);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);      //设置垂直滚动条总是出现
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);      //设置水平滚动条自动出现
        if (size != null) {
            scrollPanel.setPreferredSize(size);
        }

        return scrollPanel;
    }
}


