package com.briskhu.utilsingle.qr;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-10-30
 **/
public class QrGuiPagesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(QrGuiPagesTest.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private String text;

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
    private JLabel outputLabel = null;

    private String directory;
    private String filename;
    private final String DEFAULT_PATH = "C:\\Users\\Administrator\\Desktop";
    private String resultFile;


    @Test
    public void fileName() {
        QrGuiPagesTest test = new QrGuiPagesTest();
        test.directory = DEFAULT_PATH;
        test.filename = "test.txt";
        System.out.println(test.filename);

        test.resultFile = test.directory + File.separator + test.filename;
        System.out.println(test.filename);

        System.out.println(test.resultFile);
    }


    /* ---------------------------------------- methods ---------------------------------------- */
    public void chooseOperationPage() {
        JFrame jFrame = new JFrame("二维码生成工具");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


    public void createQrPageByBox2() {
        jFrame = initFrame("生成二维码", 800, 800);

        textLabel = initLabel("原始文本", 20);
        textField = initTextField("originText", 20, 20, TEXT_HINT);

        JPanel l1 = new JPanel();
        l1.setSize(200, 10);
        Box row1 = Box.createHorizontalBox();
        row1.add(textLabel);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(textField);
        row1.add(Box.createHorizontalGlue());
        l1.add(row1);

        dirLabel = initLabel("保存路径", 20);
        dirBtn = initButton("dirBtn", DIR_HINT, 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("[actionPerformed] Button {} has been pressed", dirBtn.getName());
                doDirChoose();
            }
        });
        Box row2 = Box.createHorizontalBox();
        row2.setSize(80, 30);
        row2.add(dirLabel);
        row2.add(dirBtn);
//        row2.add(Box.createHorizontalStrut(30));

        fileLabel = initLabel("文件名称", 20);
        fileField = initTextField("fileField", 500, 20, FILE_HINT);
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
        Box row3 = Box.createHorizontalBox();
        row3.setSize(80, 30);
        row3.add(fileLabel);
        row3.add(fileField);
        row3.add(createQrBtn);

        imageLabel = initImageLabel("qrImgLabel", 300, 300, null);
        Box row4 = Box.createHorizontalBox();
//        row4.setBounds(100, 10, 80, 30);
//        row4.add(Box.createHorizontalStrut(30));
        row4.add(imageLabel);
//        row4.add(Box.createHorizontalStrut(30));
//
//        Box box = Box.createVerticalBox();
////        box.setBounds(10, 10, 80, 150);
////        box.setSize(80, 150);
//        box.add(row1);
//        box.add(row2);
//        box.add(row3);
//        box.add(row4);

//        jFrame.setContentPane(box);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setAlignmentY();
        panel.add(l1);
        panel.add(row2);

//        jFrame.pack();
        refreshFramd(jFrame, panel);
    }



    public void createQrPageByBox() {
        jFrame = initFrame("生成二维码", 800, 800);
        textLabel = initLabel("原始文本", 20);
        textField = initTextField("originText", 120, 20, TEXT_HINT);

        Box row1 = Box.createHorizontalBox();
        Dimension rowSize = new Dimension(200, 30);
        row1.setPreferredSize(rowSize);
        row1.add(textLabel);
        row1.add(textField);
        row1.add(Box.createHorizontalGlue());

        dirLabel = initLabel("保存路径", 20);
        dirBtn = initButton("dirBtn", DIR_HINT, 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("[actionPerformed] Button {} has been pressed", dirBtn.getName());
                doDirChoose();
            }
        });
        Box row2 = Box.createHorizontalBox();
        row2.setSize(80, 30);
        row2.add(dirLabel);
        row2.add(dirBtn);
//        row2.add(Box.createHorizontalStrut(30));

        fileLabel = initLabel("文件名称", 20);
        fileField = initTextField("fileField", 500, 20, FILE_HINT);
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
        Box row3 = Box.createHorizontalBox();
        row3.setSize(80, 30);
        row3.add(fileLabel);
        row3.add(fileField);
        row3.add(createQrBtn);

        imageLabel = initImageLabel("qrImgLabel", 300, 300, null);
        Box row4 = Box.createHorizontalBox();
//        row4.setBounds(100, 10, 80, 30);
//        row4.add(Box.createHorizontalStrut(30));
        row4.add(imageLabel);
//        row4.add(Box.createHorizontalStrut(30));

        Box box = Box.createVerticalBox();
//        box.setBounds(10, 10, 80, 150);
//        box.setSize(80, 150);
        box.add(row1);
        box.add(row2);
        box.add(row3);
        box.add(row4);

        jFrame.setContentPane(box);
//        jFrame.pack();
        refreshFramd(jFrame);
    }


    public void createQrPageByGroup() {
        jFrame = initFrame("生成二维码", 800, 800);
        panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        textLabel = initLabel("原始文本", 20);
        textField = initTextField("originText", 1000, 20, TEXT_HINT);
        GroupLayout.SequentialGroup row1 = layout.createSequentialGroup();
        row1.addComponent(textLabel).addComponent(textField);
        layout.setHorizontalGroup(row1);

        GroupLayout.ParallelGroup col1 = layout.createParallelGroup();
        col1.addComponent(textLabel).addComponent(textField);
        layout.setVerticalGroup(col1);
//
//        dirLabel = init("保存路径", 20);
//        dirBtn = initButton("dirBtn", DIR_HINT, 20, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                LOGGER.info("[actionPerformed] Button {} has been pressed", dirBtn.getName());
//                doDirChoose();
//            }
//        });
//        GroupLayout.SequentialGroup row2 = layout.createSequentialGroup();
//        row2.addComponent(dirLabel).addComponent(dirBtn);
//        layout.setHorizontalGroup(row2);


//        fileLabel = init("文件名称", 20);
//        fileField = operation("fileField", 500, 20, FILE_HINT);
//        createQrBtn = initButton("createQrBtn", CREATE_QR_HINT, 20, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    LOGGER.debug("[actionPerformed] Button {} has been pressed", createQrBtn.getName());
//                    doCreateQrBtn();
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//        GroupLayout.SequentialGroup row3 = layout.createSequentialGroup();
//        row3.addComponent(fileLabel).addComponent(fileField).addComponent(createQrBtn);
//        layout.setHorizontalGroup(row3);

//        imageLabel = initImageLabel("qrImgLabel", 300, 300, null);
//        GroupLayout.SequentialGroup row4 = layout.createSequentialGroup();
//        row4.addComponent(imageLabel);
//        layout.setHorizontalGroup(row4);

//        GroupLayout.ParallelGroup col1 = layout.createParallelGroup();
//        col1.addGroup(row1).addGroup(row2).addGroup(row3).addGroup(row4);
//        layout.setVerticalGroup(col1);

        refreshFramd(jFrame, panel);
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
    private JLabel initImageLabel(String labelName, int width, int height, String imageFile) {
        JLabel result = new JLabel();
        result.setName(labelName);
        result.setSize(width, height);
        result.setText(labelName);

        ImageIcon imgIcon = new ImageIcon(imageFile);
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        result.setIcon(imgIcon);

        return result;
    }


    public void createQrPageByGridBag() {
        jFrame = initFrame("生成二维码", 800, 800);
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel = new JPanel(gridBagLayout);
        GridBagConstraints constraints = null;

        textLabel = new JLabel("原始文本");
        setLabel(textLabel, 20);
        textField = new JTextField(1000);
        setTextField(textField, TEXT_HINT, 20);
        constraints = new GridBagConstraints();
        gridBagLayout.addLayoutComponent(textLabel, constraints);
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        gridBagLayout.addLayoutComponent(textField, constraints);

        dirLabel = new JLabel("保存路径");
        setLabel(dirLabel, 20);
        dirBtn = new JButton(DIR_HINT);
        setButton(dirBtn, "dirBtn", 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("[actionPerformed] button {} has been pressed", dirBtn.getName());
                doDirChoose();
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        gridBagLayout.addLayoutComponent(dirBtn, constraints);

        fileLabel = new JLabel("文件名称");
        setLabel(fileLabel, 20);
        fileField = new JTextField(1000);
        setTextField(fileField, FILE_HINT, 20);
        createQrBtn = new JButton(CREATE_QR_HINT);
        setButton(createQrBtn, "createQrBtn", 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LOGGER.debug("[actionPerformed] button {} has been pressed", createQrBtn.getName());
                    doCreateQrBtn();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        constraints = new GridBagConstraints();
        gridBagLayout.addLayoutComponent(fileLabel, constraints);
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        gridBagLayout.addLayoutComponent(fileField, constraints);
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagLayout.addLayoutComponent(createQrBtn, constraints);

        imageLabel = new JLabel("");
        outputLabel = new JLabel("输出结果");

        addToPanel(panel);
        jFrame.setContentPane(panel);
        refreshFramd(jFrame);
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

    private void refreshFramd(JFrame frame) {
//        frame.setVisible(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refreshFramd(JFrame frame, JPanel panel) {
//        frame.setVisible(false);
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void setLabel(JLabel label, int fontSize) {
        label.setFont(new Font(null, Font.PLAIN, fontSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }


    private void setTextField(JTextField textField, String hint, int fontSize) {
        textField.setFont(new Font(null, Font.PLAIN, fontSize));
        textField.setText(hint);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void doDirChoose() {
        dirChooser = new JFileChooser(DEFAULT_PATH);
        setDirChooser(dirChooser, 20);
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

    private void setButton(JButton button, String btnName, int fontSize, ActionListener actionListener) {
        button.setFont(new Font(null, Font.PLAIN, fontSize));
        button.setName(btnName);
        button.addActionListener(actionListener);
    }


    private void doCreateQrBtn() throws Exception {
        if (checkParams()) {
            QrCodeUtil.encode(text, resultFile);
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
        return true;
    }

    private void addToPanel(JPanel panel) {
        panel.add(textLabel);
        panel.add(textField);

        panel.add(dirLabel);
        panel.add(dirBtn);
//        panel.add(dirChooser);

        panel.add(fileLabel);
        panel.add(fileField);

        panel.add(createQrBtn);
//        panel.add(imageLabel);
//        panel.add(outputLabel);
    }


    public static void scanQrPage() {
        JFrame jFrame = new JFrame("扫描二维码");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        QrGuiPages qrGuiPages = new QrGuiPages();

    }


}