package com.briskhu.utilsingle.qr;

import com.briskhu.common.jgui.operation.*;
import com.briskhu.common.jgui.operation.Button;
import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import com.briskhu.common.jgui.operation.TextField;
import com.briskhu.common.jgui.other.OsTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

/**
 * 生成二维码的操作界面<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-03
 **/
public class CreateQrPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateQrPage.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private String text;
    private static String directory;
    private String filename;
    private String fileExt = ".jpg";
    private static final String DEFAULT_PATH;
    private String resultFile;

    static {
        OsTools.printOsInfo();
        DEFAULT_PATH = OsTools.getDefaultDir("QrImages");
        directory = OsTools.createDir(DEFAULT_PATH);
    }

    private JFrame jFrame = null;
    private String framTitle = "生成二维码";
    private int frameWidth = 800;
    private int frameHeight = 800;
    private JPanel panel = null;
    private int textBarWidth = 750;
    private int textBarHeight = 50;
    private int elementGap = 10;
    private int panelGap = 10;

    private JPanel row1Panel = null;
    private JLabel textLabel = null;
    private JTextField textField = null;
    private final String TEXT_HINT = "请输入原始文本";

    private JPanel row2Panel = null;
    private JLabel dirLabel = null;
    private JFileChooser dirChooser = null;
    private int dirChooserWidth = 500;
    private int dirChooserHeight = 350;
    private JButton dirBtn = null;
    private JLabel choosedDirLabel = null;
    private final String DIR_HINT = "请选择二维码图片保存路径";

    private JPanel row3Panel = null;
    private JLabel fileLabel = null;
    private String fileLabelStr = "文件名称";
    private JTextField fileField = null;
    private final String FILE_HINT = "请指定二维码图片的名称";
    private JComboBox<String> imgFormat = null;
    private JButton createQrBtn = null;
    private final String CREATE_QR_HINT = "生成二维码";

    private JPanel row4Panel = null;
    private JLabel imageLabel = null;
    private final String IMG_HINT = "二维码图片";
    private final int imgWidth = 500;
    private final int imgHeight = 500;

    private JPanel row5Panel = null;
    private JLabel outputLabel = null;
    private String outputStr = "输出结果：";


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 基于Box布局的页面
     */
    public void createQrPageByBox() {
        LOGGER.info("[createQrPageByBox] start...");
        jFrame = Frame.init(framTitle, frameWidth, frameHeight);
        jFrame.setUndecorated(true);
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

        panel = createQrPagePanel();
        jFrame.setContentPane(panel);
//        jFrame.pack();
        Frame.showAtCenter(jFrame);

//        Frame.setWindowState(jFrame);
//        jFrame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                jFrame.setResizable(true);
//                jFrame.setPreferredSize(new Dimension(jFrame.getWidth(), jFrame.getHeight()));
//                jFrame.pack();
//                LOGGER.debug("centerX=" + Geometry.getFrameCenter(jFrame).getWidth() + ", centerY=" + Geometry.getFrameCenter(jFrame).getHeight());
////                row4Panel.setLocation((int) getFrameCenter(jFrame).getWidth(), (int) getFrameCenter(jFrame).getHeight());
//                row4Panel.setLocation((int) Geometry.getPanelCenter(panel).getWidth(), row4Panel.getY());
//                row4Panel.setSize(imgWidth, imgHeight);
//                row4Panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//                panel.add(row4Panel, 3);
//                Frame.refresh(jFrame, panel);
//            }
//        });

        LOGGER.debug("panelW=" + panel.getWidth() + ", panelH=" + panel.getHeight());
        LOGGER.debug("frameX=" + jFrame.getX() + ", frameY=" + jFrame.getY());
    }

    /**
     * 生成二维码页面的面板
     *
     * @return
     */
    public JPanel createQrPagePanel() {
        LOGGER.info("[createQrPagePanel] start...");
        JPanel resultPanel = new JPanel(null);
//        resultPanel = new JPanel();
//        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.PAGE_AXIS));
//        resultPanel.setLayout(new GridLayout());

        row1Panel = createRow1Panel("row1Panel");
        row2Panel = createRow2Panel("row2Panel");
        row3Panel = createRow3Panel("row3Panel");
        row4Panel = createRow4Panel("row4Panel");
        row5Panel = createRow5Panel("row5Panel");

        LOGGER.debug("[createQrPagePanel] resultPanel: (x,y)=({},{})", resultPanel.getX(), resultPanel.getY());
        row1Panel.setLocation(resultPanel.getX() + panelGap, resultPanel.getY() + panelGap);
        row1Panel.setSize(textBarWidth, textBarHeight);
        row2Panel.setLocation(10, textBarHeight + panelGap * 2);
        row2Panel.setSize(textBarWidth, textBarHeight);
        row3Panel.setLocation(10, (textBarHeight + panelGap) * 2 + panelGap);
        row3Panel.setSize(textBarWidth, textBarHeight);

        row4Panel.setLocation(150, (textBarHeight + panelGap) * 3 + panelGap);
        row4Panel.setSize(imgWidth, imgHeight);
        row5Panel.setLocation(10, row4Panel.getY() + imgHeight + panelGap);
        row5Panel.setSize(textBarWidth, textBarHeight);

//        GuiDebugTools.printBorder(Color.GREEN, row1Panel, row2Panel, row3Panel, row4Panel, row5Panel);
        LOGGER.debug("[createQrPagePanel] resultPanel: w = {}, h = {}", resultPanel.getWidth(), resultPanel.getHeight());
        Panel.add(resultPanel, row1Panel, row2Panel, row3Panel, row4Panel, row5Panel);
        panel = resultPanel;

        return resultPanel;
    }


    private JPanel createRow1Panel(String panelName) {
        textLabel = Label.init("原始文本", 20);
        textField = TextField.init("originText", 40, 20, TEXT_HINT);

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
        dirBtn.setPreferredSize(new Dimension(280, 40));
        Box box = Box.createHorizontalBox();
        box.add(dirLabel);
        box.add(Box.createHorizontalStrut(10));
        box.add(dirBtn);

        return Panel.initForBox(panelName, 200, 10, box);
    }

    private JPanel createRow3Panel(String panelName) {
        fileLabel = Label.init(fileLabelStr, 20);
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

        String[] imgFormats = new String[]{".jpg", ".png", ".jpeg"};
        imgFormat = ComboBox.initForString("imgFormat", 20, new Dimension(80, 10), imgFormats, new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fileExt = (String) imgFormat.getSelectedItem();
            }
        });
        Box box = Box.createHorizontalBox();
        box.add(fileLabel);
        box.add(Box.createHorizontalStrut(10));
        box.add(fileField);
        box.add(Box.createHorizontalStrut(10));
        box.add(imgFormat);
        box.add(Box.createHorizontalStrut(95));
        box.add(createQrBtn);

        return Panel.initForBox(panelName, 200, 10, box);
    }

    private JPanel createRow4Panel(String panelName) {
        imageLabel = Label.initImageLabel("qrImgLabel", IMG_HINT, imgWidth, imgHeight, null);
        JPanel panel = Panel.init(panelName, imgWidth, imgHeight);
        panel.setLayout(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRow5Panel(String panelName) {
        outputLabel = Label.init(outputStr, 20);
        JPanel panel = Panel.init(panelName, 200, 10);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(outputLabel);

        return panel;
    }


    private void doDirChoose() {
        dirChooser = new JFileChooser(DEFAULT_PATH);
        initDirChooser(dirChooser, 20);
    }

    private void initDirChooser(JFileChooser dirChooser, int fontSize) {
        dirChooser.setFont(new Font(null, Font.PLAIN, fontSize));
        dirChooser.setPreferredSize(new Dimension(dirChooserWidth, dirChooserHeight));
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        dirChooser.setDialogTitle(DIR_HINT);
        dirChooser.setApproveButtonText("确定");
        int result = dirChooser.showOpenDialog(dirChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            directory = dirChooser.getSelectedFile().getAbsolutePath();
            LOGGER.info("选择的二维码图片保存路径为：{}", directory);
        } else {
            directory = DEFAULT_PATH;
        }

        if (choosedDirLabel == null) {
            choosedDirLabel = Label.init(directory, 20);
            choosedDirLabel.setPreferredSize(new Dimension(345, 20));
            row2Panel = (JPanel) panel.getComponent(1);
            Box box = ((Box) row2Panel.getComponent(0));
            box.add(choosedDirLabel, 2);
            box.add(Box.createHorizontalStrut(20), 3);
            row2Panel.remove(0);
            row2Panel.add(box);
            Panel.reloadChildren(panel, row1Panel, row2Panel, row3Panel, row4Panel, row5Panel);
        } else {
            choosedDirLabel.setText(directory);
            row2Panel.validate();
        }
//        Frame.refresh(jFrame, panel);
    }

    private void doCreateQrBtn() throws Exception {
        if (checkParams()) {
            QrCodeUtil.encode(text, resultFile);
            row4Panel = (JPanel) panel.getComponent(3);
//            for (int i = 0; i < panel.getComponents().length; i++) {
////                LOGGER.debug("[doCreateQrBtn] panel components: ", panel.getComponent(i).toString());
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
        filename = fileField.getText() + fileExt;
        resultFile = directory + File.separator + filename;
        LOGGER.info("[checkParams] text = {}, resultFile ={}", text, resultFile);
        outputLabel.setText(outputStr + resultFile);
        outputLabel.validate();

        return true;
    }

    public void setJFrame(JFrame frame) {
        jFrame = frame;
    }


    public static void main(String[] args) {
        CreateQrPage createQrPage = new CreateQrPage();

        createQrPage.createQrPageByBox();
    }
}


