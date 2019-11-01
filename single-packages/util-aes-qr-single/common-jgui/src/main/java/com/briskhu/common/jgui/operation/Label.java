package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 标签的基本操作工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class Label {
    private static final Logger LOGGER = LoggerFactory.getLogger(Label.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 初始化标签
     * 不指定Label的name属性时，name属性的值为“null”，这点与Frame的特点不一样。
     *
     * @param labelStr
     * @param fontSize
     * @return
     */
    public static JLabel init(String labelStr, int fontSize) {
        return init(labelStr, null, fontSize);
    }

    /**
     * 初始化标签
     *
     * @param labelStr
     * @param labelName
     * @param fontSize
     * @return
     */
    public static JLabel init(String labelStr, String labelName, int fontSize) {
        JLabel label = new JLabel(labelStr);
        label.setName(labelName);
        init(label, fontSize);

        return label;
    }

    public static void init(JLabel label, int fontSize) {
        label.setFont(new Font(null, Font.PLAIN, fontSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }

    /**
     * 初始化图片标签
     *
     * @param labelName
     * @param hint
     * @param width
     * @param height
     * @param imageFile
     * @return
     */
    public static JLabel initImageLabel(String labelName, String hint, int width, int height, String imageFile) {
        JLabel result = initImageLabel(labelName, width, height, imageFile);
        result.setText(hint);

        return result;
    }

    /**
     * 初始化图片标签
     *
     * @param labelName
     * @param width
     * @param height
     * @param imageFile
     * @return
     */
    public static JLabel initImageLabel(String labelName, int width, int height, String imageFile) {
        JLabel result = new JLabel();
        result.setName(labelName);
        result.setPreferredSize(new Dimension(width, height));
//        result.setSize(width, height);

        ImageIcon imgIcon = new ImageIcon(imageFile);
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        result.setIcon(imgIcon);

        return result;
    }


}


