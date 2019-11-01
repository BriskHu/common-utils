package com.briskhu.common.jgui.operation;

import org.junit.Test;

import javax.swing.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class FrameTest {

    @Test
    public void initFrame() {
        JFrame frame = new JFrame();
        System.out.println(frame.getName());
        frame.setName("1");
        System.out.println(frame.getName());

        JLabel label = new JLabel();
        System.out.println(label.getName());
        label.setName("2");
        System.out.println(label.getName());

        JTextField textField = new JTextField();
        System.out.println(textField.getName());
        textField.setName("3");
        System.out.println(textField.getName());

        JPanel panel = new JPanel();
        System.out.println(panel.getName());
        panel.setName("4");
        System.out.println(panel.getName());
    }
}