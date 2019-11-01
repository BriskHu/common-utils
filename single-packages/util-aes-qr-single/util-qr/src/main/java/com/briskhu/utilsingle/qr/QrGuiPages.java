package com.briskhu.utilsingle.qr;

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
public class GuiPages {
    private static final Logger LOGGER = LoggerFactory.getLogger(GuiPages.class);

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
        jFrame = initFrame("生成二维码", 800, 800);

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
        refreshFramd(jFrame, panel);

        jFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println("centerX=" + getFrameCenter(jFrame).getWidth() + ", centerY=" + getFrameCenter(jFrame).getHeight());
//                row4Panel.setLocation((int) getFrameCenter(jFrame).getWidth(), (int) getFrameCenter(jFrame).getHeight());
                row4Panel.setLocation((int) getCurrentCenter(panel).getWidth(), (int) getCurrentCenter(panel).getHeight());
                row4Panel.setSize(imgWidth, imgHeight);
                panel.add(row4Panel);
                refreshFramd(jFrame, panel);
                super.componentResized(e);
            }
        });

        System.out.println("panelW=" + panel.getWidth() + ", panelH=" + panel.getHeight());
        System.out.println("frameX=" + jFrame.getX() + ", frameY=" + jFrame.getY());
    }


    /**
     * 初始化窗口
     *
     * @param frameName
     * @param width
     * @param height
     * @return
     */
    private JFrame initFrame(String frameName, int width, int height) {
        JFrame frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        return frame;
    }

    private JPanel createRow1Panel(String panelName) {
        textLabel = initLabel("原始文本", 20);
        textField = initTextField("originText", 36, 20, TEXT_HINT);

        Box box = Box.createHorizontalBox();
        box.add(textLabel);
        box.add(Box.createHorizontalStrut(10));
        box.add(textField);

        return initPanel(panelName, 200, 5, box);
    }

    private JPanel initPanel(String panelName, int width, int height) {
        JPanel result = new JPanel();
        result.setName(panelName);
        result.setPreferredSize(new Dimension(width, height));
//        result.setSize(width, height);
        return result;
    }

    private JPanel initPanel(String panelName, int width, int height, Box box) {
        JPanel result = initPanel(panelName, width, height);
//        result.setSize(width, height);
        result.setLayout(new FlowLayout(FlowLayout.LEFT));

        result.add(box);
        return result;
    }

    private JPanel createRow2Panel(String panelName) {
        dirLabel = initLabel("保存路径", 20);
        dirBtn = initButton("dirBtn", DIR_HINT, 20, new ActionListener() {
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

        return initPanel(panelName, 200, 10, box);
    }

    private JPanel createRow3Panel(String panelName) {
        fileLabel = initLabel("文件名称", 20);
        fileField = initTextField("fileField", 20, 20, FILE_HINT);
        createQrBtn = initButton("createQrBtn", CREATE_QR_HINT, 20, new ActionListener() {
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

        return initPanel(panelName, 200, 10, box);
    }

    private JPanel createRow4Panel(String panelName) {
        imageLabel = initImageLabel("qrImgLabel", "二维码图片", imgWidth, imgHeight, null);

        JPanel panel = initPanel(panelName, imgWidth, imgHeight);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(imageLabel);
        panel.validate();

        return panel;
    }

    /**
     * 初始化标签
     *
     * @param labelStr
     * @param fontSize
     * @return
     */
    private JLabel initLabel(String labelStr, int fontSize) {
        JLabel label = new JLabel(labelStr);
        label.setFont(new Font(null, Font.PLAIN, fontSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        return label;
    }

    /**
     * 初始化文本框
     *
     * @param fieldName
     * @param columns
     * @param fontSize
     * @param hint
     * @return
     */
    private JTextField initTextField(String fieldName, int columns, int fontSize, String hint) {
        JTextField textField = new JTextField(columns);
        textField.setName(fieldName);
        textField.setFont(new Font(null, Font.PLAIN, fontSize));
        textField.setText(hint);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        return textField;
    }

    /**
     * 初始化按钮
     *
     * @param btnName
     * @param btnLabel
     * @param fontSize
     * @param actionListener
     * @return
     */
    private JButton initButton(String btnName, String btnLabel, int fontSize, ActionListener actionListener) {
        JButton button = new JButton(btnLabel);
        button.setFont(new Font(null, Font.PLAIN, fontSize));
        button.setName(btnName);
        button.addActionListener(actionListener);

        return button;
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
    private JLabel initImageLabel(String labelName, String hint, int width, int height, String imageFile) {
        JLabel result = new JLabel();
        result.setName(labelName);
        result.setPreferredSize(new Dimension(width, height));
//        result.setSize(width, height);
        result.setText(hint);

        ImageIcon imgIcon = new ImageIcon(imageFile);
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        result.setIcon(imgIcon);

        return result;
    }


    private void refreshFramd(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refreshFramd(JFrame frame, JPanel panel) {
//        frame.setVisible(false);
        frame.setContentPane(panel);
        refreshFramd(frame);
    }

    /**
     * 计算窗口中心点坐标
     *
     * @param frame
     * @return 坐标值为double型
     */
    private Dimension getFrameCenter(JFrame frame) {
        Dimension result = new Dimension();

        System.out.println("frame: " + frame.getWidth() + "," + frame.getHeight());
        double centerX = (frame.getWidth() + frame.getX()) / 2;
        double centerY = (frame.getHeight() + frame.getY()) / 2;
        result.setSize(centerX, centerY);

        return result;
    }

    /**
     * 计算窗口当前大小的中心点坐标
     *
     * @param panel
     * @return 坐标值为double型
     */
    private Dimension getCurrentCenter(JPanel panel) {
        Dimension result = new Dimension();

        double centerX = (panel.getWidth() + panel.getX()) / 2;
        double centerY = (panel.getHeight() + panel.getY()) / 2;
        result.setSize(centerX, centerY);

        return result;
    }

    private void setLabel(JLabel label, int fontSize) {
        label.setFont(new Font(null, Font.PLAIN, fontSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
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

        choosedDirLabel = new JLabel();
        setLabel(choosedDirLabel, 20);
        choosedDirLabel.setText(directory);
        panel.remove(dirBtn);
        panel.add(choosedDirLabel, 3);
        refreshFramd(jFrame, panel);
    }

    private void initDirChooser(JFileChooser dirChooser, int fontSize){
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

        if (choosedDirLabel == null){
            choosedDirLabel = new JLabel();
            setLabel(choosedDirLabel, 20);
            choosedDirLabel.setPreferredSize(new Dimension(850, 20));
            choosedDirLabel.setText(directory);
            row2Panel = (JPanel) panel.getComponent(1);
            Box box = ((Box)row2Panel.getComponent(0));
            box.add(choosedDirLabel, 2);
            box.add(Box.createHorizontalStrut(20), 3);
            row2Panel.remove(0);
            row2Panel.add(box);
            reloadChildren(panel, row1Panel, row2Panel, row3Panel, row4Panel);
        }
        else {
            choosedDirLabel.setText(directory);
            row2Panel.validate();
        }

        refreshFramd(jFrame, panel);
    }

    private void reloadChildren(JPanel rootPanel, JPanel... childrens){
        for (int i=0; i<childrens.length; i++){
            rootPanel.remove(childrens[i]);
            rootPanel.add(childrens[i]);
        }
    }

    private void setButton(JButton button, String btnName, int fontSize, ActionListener actionListener) {
        button.setFont(new Font(null, Font.PLAIN, fontSize));
        button.setName(btnName);
        button.addActionListener(actionListener);
    }


    private void doCreateQrBtn() throws Exception {
        if (checkParams()) {
            QrCodeUtil.encode(text, resultFile);
            row4Panel = (JPanel) panel.getComponent(3);
//            for (int i=0; i< panel.getComponents().length; i++){
//                System.out.println(panel.getComponent(i));
//            }

            JLabel imgLabel = (JLabel) row4Panel.getComponent(0);
            imgLabel.setIcon(new ImageIcon(resultFile));
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
        resultFile = directory + File.separator + fileField;
        LOGGER.info("[checkParams] text = {}, resultFile ={}", text, resultFile);

        return true;
    }


    public static void scanQrPage() {
        JFrame jFrame = new JFrame("扫描二维码");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        GuiPages guiPages = new GuiPages();

        guiPages.createQrPageByBox();
    }
}


