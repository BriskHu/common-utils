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

    public static void init(JTextArea textAreaStr, int fontSize) {
        textAreaStr.setFont(new Font(null, Font.PLAIN, fontSize));
    }

}


