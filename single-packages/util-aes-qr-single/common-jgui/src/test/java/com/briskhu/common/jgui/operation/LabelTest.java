package com.briskhu.common.jgui.operation;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class LabelTest {

    @Test
    public void init() {
        JLabel label = Label.init("文本", 20);
        System.out.println(label.getText());
        System.out.println(label.getName());
    }

    public static void main(String[] args) {
        JLabel label = Label.init("文本", 20);
        System.out.println(label.getText());

        JFrame frame = Frame.init("test", 300, 300);
        JPanel panel = new JPanel();
        panel.add(label);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}