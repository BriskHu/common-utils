package com.briskhu.common.jgui.algorithm;

import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import javafx.scene.layout.Pane;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class GeometryTest {

    @Test
    public void getPanelBorder() {

    }

    public static void main(String[] args) {
        JFrame frame = Frame.init("test", 300, 300);
        JPanel p1 = Panel.initByFlow("p1", 200, 100);
        JLabel l1 = Label.init("label 1 ", 20);
        l1.setBorder(BorderFactory.createLineBorder(Color.RED));
        p1.setBorder(BorderFactory.createLineBorder(Color.RED));
        p1.add(l1);


        JPanel p2 = Panel.init("p2", 200, 80);
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        JLabel l2 = Label.init("Label 2", 20);
        l2.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        p2.add(l2);
        JLabel l3 = Label.init("Label 3", 20);
        l3.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Geometry.putInCenter(p2, l3);
        p2.add(l3);
        JPanel panel = new JPanel();
        panel.add(p1);
        panel.add(p2);
        frame.setContentPane(panel);
        frame.setVisible(true);

        System.out.println("p2:" + p2.getX()+","+p2.getY());
        System.out.println("p2 center:" + Geometry.getPanelCenter(p2));
        System.out.println("l2 size:" + l2.getSize());
        System.out.println("l2:" + l2.getX()+","+l2.getY());
        System.out.println("l3:" + l3.getX()+","+l3.getY());
    }
}