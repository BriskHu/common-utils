package com.briskhu.utilsingle.qr;

import com.briskhu.common.jgui.algorithm.Geometry;
import com.briskhu.common.jgui.operation.Button;
import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import com.briskhu.common.jgui.operation.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-10-30
 **/
public class QrGuiPages {
    private static final Logger LOGGER = LoggerFactory.getLogger(QrGuiPages.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private String text;
    private String directory;
    private String filename;
    private final String DEFAULT_PATH = "C:\\Users\\Administrator\\Desktop";
    private String resultFile;

    JFrame jFrame = null;
    JPanel panel = null;
    private JLabel textLabel = null;
    private JTextField textField = null;
    private final String TEXT_HINT = "请输入原始文本";

    private JLabel dirLabel = null;
    private JFileChooser dirChooser = null;
    private JButton dirBtn = null;
    private JLabel choosedDirLabel = null;
    private final String DIR_HINT = "请选择二维码图片保存路径";

    private JLabel fileLabel = null;
    private JTextField fileField = null;
    private final String FILE_HINT = "请指定二维码图片的名称";

    private JButton createQrBtn = null;
    private final String CREATE_QR_HINT = "生成二维码";

    private JLabel imageLabel = null;
    private final int imgWidth = 300;
    private final int imgHeight = 300;
    private JLabel outputLabel = null;

    private JPanel row1Panel = null;
    private JPanel row2Panel = null;
    private JPanel row3Panel = null;
    private JPanel row4Panel = null;


    /* ---------------------------------------- methods ---------------------------------------- */
    public void chooseOperationPage() {
        JFrame jFrame = new JFrame("二维码生成工具");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


    public void createQrPageByBox() {
        jFrame = Frame.init("生成二维码", 800, 800);

        row1Panel = createRow1Panel("row1Panel");
        row2Panel = createRow2Panel("row2Panel");
        row3Panel = createRow3Panel("row3Panel");
        row4Panel = createRow4Panel("row4Panel");

//        panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//        panel.setLayout(new GridLayout());
        panel = new JPanel(null);
        System.out.println("x=" + panel.getX() + ", y=" + panel.getY());
        row1Panel.setLocation(panel.getX() + 10, panel.getY() + 10);
        row1Panel.setSize(1000, 50);
        row2Panel.setLocation(10, 60);
        row2Panel.setSize(1000, 50);
        row3Panel.setLocation(10, 120);
        row3Panel.setSize(1000, 50);

//        panel.invalidate();
        System.out.println("panelW=" + panel.getWidth() + ", panelH=" + panel.getHeight());
        panel.add(row1Panel);
        panel.add(row2Panel);
        panel.add(row3Panel);
//        jFrame.pack();
        Frame.refresh(jFrame, panel);

        jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println("centerX=" + Geometry.getFrameCenter(jFrame).getWidth() + ", centerY=" + Geometry.getFrameCenter(jFrame).getHeight());
//                row4Panel.setLocation((int) getFrameCenter(jFrame).getWidth(), (int) getFrameCenter(jFrame).getHeight());
                row4Panel.setLocation((int) Geometry.getPanelCenter(panel).getWidth(), (int) Geometry.getPanelCenter(panel).getHeight());
                row4Panel.setSize(imgWidth, imgHeight);
                panel.add(row4Panel);
                Frame.refresh(jFrame, panel);
                super.componentResized(e);
            }
        });

        System.out.println("panelW=" + panel.getWidth() + ", panelH=" + panel.getHeight());
        System.out.println("frameX=" + jFrame.getX() + ", frameY=" + jFrame.getY());
    }


    private JPanel createRow1Panel(String panelName) {
        textLabel = Label.init("原始文本", 20);
        textField = TextField.init("originText", 36, 20, TEXT_HINT);

        Box box = Box.createHorizontalBox();
        box.add(textLabel);
        box.add(Box.createHorizontalStrut(10));
        box.add(textField);

        return Panel.initForBox(panelName, 200, 5, box);
    }



    private JPanel createRow2Panel(String panelName) {
        dirLabel = Label.init("保存路径", 20);
        dirBtn = Button.init("dirBtn", DIR_HINT, 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("[actionPerformed] Button {} has been pressed", dirBtn.getName());
                doDirChoose();
            }
        });
        dirBtn.setPreferredSize(new Dimension(300, 40));
        Box box = Box.createHorizontalBox();
        box.add(dirLabel);
        box.add(Box.createHorizontalStrut(10));
        box.add(dirBtn);

        return Panel.initForBox(panelName, 200, 10, box);
    }

    private JPanel createRow3Panel(String panelName) {
        fileLabel = Label.init("文件名称", 20);
        fileField = TextField.init("fileField", 20, 20, FILE_HINT);
        createQrBtn = Button.init("createQrBtn", CREATE_QR_HINT, 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LOGGER.debug("[actionPerformed] Button {} has been pressed", createQrBtn.getName());
                    doCreateQrBtn();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Box box = Box.createHorizontalBox();
        box.add(fileLabel);
        box.add(Box.createHorizontalStrut(10));
        box.add(fileField);
        box.add(Box.createHorizontalStrut(118));
        box.add(createQrBtn);

        return Panel.initForBox(panelName, 200, 10, box);
    }

    private JPanel createRow4Panel(String panelName) {
        imageLabel = Label.initImageLabel("qrImgLabel", "二维码图片", imgWidth, imgHeight, null);
        JPanel panel = Panel.init(panelName, imgWidth, imgHeight);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(imageLabel);

        return panel;
    }


    private void doDirChoose() {
        dirChooser = new JFileChooser(DEFAULT_PATH);
//        setDirChooser(dirChooser, 20);
        initDirChooser(dirChooser, 20);
    }

    private void setDirChooser(JFileChooser dirChooser, int fontSize) {
        dirChooser.setFont(new Font(null, Font.PLAIN, fontSize));
        dirChooser.setSize(500, 500);
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        dirChooser.setDialogTitle(DIR_HINT);
        dirChooser.setApproveButtonText("确定");
        int result = dirChooser.showOpenDialog(dirChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.directory = dirChooser.getSelectedFile().getAbsolutePath();
            LOGGER.info("选择的二维码图片保存路径为：{}", directory);
        } else {
            this.directory = DEFAULT_PATH;
        }

        choosedDirLabel = Label.init(directory, 20);
        panel.remove(dirBtn);
        panel.add(choosedDirLabel, 3);
        Frame.refresh(jFrame, panel);
    }

    private void initDirChooser(JFileChooser dirChooser, int fontSize) {
        dirChooser.setFont(new Font(null, Font.PLAIN, fontSize));
        dirChooser.setPreferredSize(new Dimension(500, 500));
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        dirChooser.setDialogTitle(DIR_HINT);
        dirChooser.setApproveButtonText("确定");
        int result = dirChooser.showOpenDialog(dirChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.directory = dirChooser.getSelectedFile().getAbsolutePath();
            LOGGER.info("选择的二维码图片保存路径为：{}", directory);
        } else {
            this.directory = DEFAULT_PATH;
        }

        if (choosedDirLabel == null) {
            choosedDirLabel = Label.init(directory, 20);
            choosedDirLabel.setPreferredSize(new Dimension(850, 20));
            row2Panel = (JPanel) panel.getComponent(1);
            Box box = ((Box) row2Panel.getComponent(0));
            box.add(choosedDirLabel, 2);
            box.add(Box.createHorizontalStrut(20), 3);
            row2Panel.remove(0);
            row2Panel.add(box);
            Panel.reloadChildren(panel, row1Panel, row2Panel, row3Panel, row4Panel);
        } else {
            choosedDirLabel.setText(directory);
            row2Panel.validate();
        }

        Frame.refresh(jFrame, panel);
    }

    private void doCreateQrBtn() throws Exception {
        if (checkParams()) {
            QrCodeUtil.encode(text, resultFile);
            row4Panel = (JPanel) panel.getComponent(3);
//            for (int i=0; i< panel.getComponents().length; i++){
//                System.out.println(panel.getComponent(i));
//            }

            row4Panel.remove(0);
            imageLabel = null;
            imageLabel = Label.initImageLabel("qrImgLabel", imgWidth, imgHeight, resultFile);
            row4Panel.add(imageLabel);
            row4Panel.validate();
        }
    }

    private boolean checkParams() {
        if (textField.getText().equals(TEXT_HINT)) {
            LOGGER.error("[checkParams] 原始文本为空。");

        } else if (fileField.getText().equals(FILE_HINT)) {
            LOGGER.error("[checkParams] 文件名为空。");

        } else if (directory == null) {
            LOGGER.error("[checkParams] 保存路径为空。");
        }

        text = textField.getText();
        filename = fileField.getText();
        resultFile = directory + File.separator + filename;
        LOGGER.info("[checkParams] text = {}, resultFile ={}", text, resultFile);

        return true;
    }


    public static void scanQrPage() {
        JFrame jFrame = new JFrame("扫描二维码");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        QrGuiPages qrGuiPages = new QrGuiPages();

        qrGuiPages.createQrPageByBox();
    }
}


