package com.briskhu.utilsingle.qr;

import com.briskhu.common.jgui.operation.Button;
import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import com.briskhu.common.jgui.operation.TextArea;
import com.briskhu.common.jgui.other.FileTypeUtils;
import com.google.zxing.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 扫描二维码的操作界面<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-03
 **/
public class ScanQrPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanQrPage.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private String qrDecryptedText;
    private String filename;
    private File choosedFile;
    private final String DEFAULT_PATH = "C:\\Users\\Administrator\\Desktop";

    private JFrame jFrame = null;
    private final String FRAME_TITLE = "扫描二维码";
    private int frameWidth = 800;
    private int frameHeight = 800;
    private JPanel panel = null;
    private int textBarWidth = 700;
    private int textBarHeight = 50;
    private int elementGap = 10;
    private int panelGap = 10;
    private JPanel[] rowPanels = null;
    /**
     * 正常字体尺寸
     */
    private final int FSIZE_NORMAL = 20;

    private JPanel row1Panel = null;
    private JButton chooseFileBtn = null;
    private JFileChooser fileChooser = null;
    private final String CHOOSE_FILE_HINT = "请选择二维码图片";
    private final String NOT_FILE = "选择的不是一个有效的文件";
    private final String NOT_IMAGE = "选择的不是一个有效的jpg/png文件";
    private final String NO_FILE = "没有选择图片文件";
    private final String NO_QR_IMAGE = "选择的不是一个有效的二维码图片文件";
    private int fileChooserWidth = 500;
    private int fileChooserHeight = 350;
    private JButton scanQrBtn = null;
    private final String SCAN_QR_HINT = "扫描二维码";

    private JPanel row2Panel = null;
    private JLabel fileHintLabel = null;
    private String fileHintLabelStr = "文件名称：";
    private JLabel filenameLabel = null;

    private JPanel row3Panel = null;
    private JLabel imageLabel = null;
    private final String IMG_HINT = "二维码图片";
    private final int imgWidth = 500;
    private final int imgHeight = 500;

    private JPanel row4Panel = null;
    private JLabel outputLabel = null;
    private final String outputStr = "扫描结果：";

    private JPanel row5Panel = null;
    private JTextArea qrDecryptedTextArea = null;
    private int qrDecryptedTextAreaWidth = 600;
    private int qrDecryptedTextAreaHeight = 100;


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 基于Box布局的页面
     */
    public void scanQrPageByBox() {
        jFrame = Frame.init(FRAME_TITLE, frameWidth, frameHeight);
        jFrame.setUndecorated(true);
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

        panel = scanQrPagePanel();
        jFrame.setContentPane(panel);
        Frame.showAtCenter(jFrame);
    }

    /**
     * 生成二维码页面的面板
     *
     * @return
     */
    public JPanel scanQrPagePanel() {
        JPanel resultPanel = new JPanel(null);

        row1Panel = createRow1Panel("row1Panel");
        row2Panel = createRow2Panel("row2Panel");
        row3Panel = createRow3Panel("row3Panel");
        row4Panel = createRow4Panel("row4Panel");
        row5Panel = createRow5Panel("row5Panel");
        rowPanels = new JPanel[]{row1Panel, row2Panel, row3Panel, row4Panel, row5Panel};

        LOGGER.debug("[scanQrPagePanel] resultPanel: (x,y)=({},{})", resultPanel.getX(), resultPanel.getY());
        row1Panel.setLocation(resultPanel.getX() + panelGap, resultPanel.getY() + panelGap);
        row1Panel.setSize(textBarWidth, textBarHeight);
        row2Panel.setLocation(10, textBarHeight + panelGap * 2);
        row2Panel.setSize(textBarWidth, textBarHeight);
        row3Panel.setLocation(150, (textBarHeight + panelGap) * 2 + panelGap);
        row3Panel.setSize(imgWidth, imgHeight);

        row4Panel.setLocation(10, (textBarHeight + panelGap) * 2 + imgHeight + panelGap);
        row4Panel.setSize(100, textBarHeight);
        row5Panel.setLocation(110, (textBarHeight + panelGap) * 2 + imgHeight + panelGap);
        row5Panel.setSize(qrDecryptedTextAreaWidth, qrDecryptedTextAreaHeight);

//        GuiDebugTools.printBorder(Color.GREEN, rowPanels);
        LOGGER.debug("[scanQrPagePanel] resultPanel: w = {}, h = {}", resultPanel.getWidth(), resultPanel.getHeight());
        Panel.add(resultPanel, rowPanels);
        panel = resultPanel;

        return resultPanel;
    }


    private JPanel createRow1Panel(String panelName) {
        chooseFileBtn = Button.init("chooseFileBtn", CHOOSE_FILE_HINT, 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("[actionPerformed] Button {} has been pressed", chooseFileBtn.getName());
                doFileChoose();
            }
        });
        chooseFileBtn.setPreferredSize(new Dimension(380, 40));

        scanQrBtn = Button.init("scanQrBtn", SCAN_QR_HINT, 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LOGGER.debug("[actionPerformed] Button {} has been pressed", scanQrBtn.getName());
                    doScanQrBtn();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        scanQrBtn.setPreferredSize(new Dimension(100, 40));
        scanQrBtn.setEnabled(false);

        Box box = Box.createHorizontalBox();
        box.add(chooseFileBtn);
        box.add(Box.createHorizontalStrut(360));
        box.add(scanQrBtn);

        return Panel.initForBox(panelName, 200, 10, box);
    }

    private JPanel createRow2Panel(String panelName) {
        fileHintLabel = Label.init(fileHintLabelStr, "filenameLabel", 20);
        filenameLabel = Label.init("", "filenameLabel", 20);

        Box box = Box.createHorizontalBox();
        box.add(fileHintLabel);
        box.add(filenameLabel);

        return Panel.initForBox(panelName, 200, 10, box);
    }

    private JPanel createRow3Panel(String panelName) {
        imageLabel = Label.initImageLabel("imageLabel", IMG_HINT, imgWidth, imgHeight, null);
        JPanel panel = Panel.init(panelName, imgWidth, imgHeight);
        panel.setLayout(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRow4Panel(String panelName) {
        outputLabel = Label.init(outputStr, FSIZE_NORMAL);
        JPanel panel = Panel.init(panelName, imgWidth, imgHeight);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(outputLabel);

        return panel;
    }

    private JPanel createRow5Panel(String panelName) {
        qrDecryptedTextArea = TextArea.init("null", "qrDecryptedTextArea", FSIZE_NORMAL);
        qrDecryptedTextArea.setPreferredSize(new Dimension(qrDecryptedTextAreaWidth, qrDecryptedTextAreaHeight));
        qrDecryptedTextArea.enableInputMethods(false);

        JPanel panel = Panel.init(panelName, imgWidth, imgHeight);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(qrDecryptedTextArea);

        return panel;
    }


    private void doFileChoose() {
        fileChooser = new JFileChooser(DEFAULT_PATH);
        initFileChooser(fileChooser, FSIZE_NORMAL);
    }

    private void initFileChooser(JFileChooser fileChooser, int fontSize) {
        this.qrDecryptedText = null;
        this.choosedFile = null;
        this.filename = null;

        fileChooser.setFont(new Font(null, Font.PLAIN, fontSize));
        fileChooser.setPreferredSize(new Dimension(fileChooserWidth, fileChooserHeight));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.setDialogTitle(CHOOSE_FILE_HINT);
        fileChooser.setApproveButtonText("确定");
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.choosedFile = fileChooser.getSelectedFile();
            if (!choosedFile.exists() || !choosedFile.isFile()) {
                LOGGER.error("[initFileChooser] {}。", NOT_FILE);
                JOptionPane.showMessageDialog(panel, NOT_FILE, CHOOSE_FILE_HINT, JOptionPane.ERROR_MESSAGE);
//                JOptionPane.showMessageDialog(null, NOT_FILE, CHOOSE_FILE_HINT, JOptionPane.ERROR_MESSAGE);
                row3Panel.validate();
            }
            String fileType = FileTypeUtils.getFileType(choosedFile);
            LOGGER.debug("[initFileChooser] fileType = {}", fileType);

            if (fileType == null || (!fileType.equals("jpg") && !fileType.equals("png"))) {
                LOGGER.error("[initFileChooser] {}。", NOT_IMAGE);
                JOptionPane.showMessageDialog(panel, NOT_IMAGE, CHOOSE_FILE_HINT, JOptionPane.ERROR_MESSAGE);
                scanQrBtn.setEnabled(false);
                refreshImgLabel(null);
            } else {
                scanQrBtn.setEnabled(true);
                this.choosedFile = choosedFile.getAbsoluteFile();
                this.filename = choosedFile.getAbsolutePath();
                LOGGER.info("[initFileChooser] 选择的二维码图片为：choosedFile = {}, filename = {}, choosedFile.getName() = {}", choosedFile, filename, choosedFile.getName());

                refreshImgLabel(filename);
            }
        } else {
            LOGGER.error("[initFileChooser] jFrame= {}, msg = {}。", jFrame, NO_FILE);
            JOptionPane.showMessageDialog(panel, NO_FILE, CHOOSE_FILE_HINT, JOptionPane.INFORMATION_MESSAGE);
            row3Panel.validate();
        }

        qrDecryptedTextArea.setText(qrDecryptedText);
        filenameLabel.setText(filename);
        row1Panel.validate();
    }

    /**
     * 刷新图片标签
     * @param filename
     */
    private void refreshImgLabel(String filename){
        row3Panel.remove(0);
        imageLabel = null;
        imageLabel = Label.initImageLabel("imageLabel", imgWidth, imgHeight, filename);
        row3Panel.add(imageLabel, BorderLayout.CENTER);
        row3Panel.validate();
        Panel.reloadChildren(panel, rowPanels);
    }

    private void doScanQrBtn() throws Exception {
        if (checkParams()) {
            try{
                qrDecryptedText = QrCodeUtil.decode(choosedFile);
                if (qrDecryptedText == null){
                    JOptionPane.showMessageDialog(panel, NO_QR_IMAGE, CHOOSE_FILE_HINT, JOptionPane.INFORMATION_MESSAGE);
                }else {
                    qrDecryptedTextArea.setText(qrDecryptedText);
                    row4Panel.validate();
                }
            }catch (NotFoundException nfe){
                LOGGER.error("[doScanQrBtn] {}", NO_QR_IMAGE);
                JOptionPane.showMessageDialog(panel, NO_QR_IMAGE, CHOOSE_FILE_HINT, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean checkParams() {
        if (this.filename == null || filename.equals("")) {
            LOGGER.error("[initFileChooser] jFrame= {}, msg = {}。", jFrame, NO_FILE);
            JOptionPane.showMessageDialog(panel, NO_FILE, CHOOSE_FILE_HINT, JOptionPane.INFORMATION_MESSAGE);
            row3Panel.validate();
            return false;
        }

        return true;
    }

    public void setJFrame(JFrame frame) {
        jFrame = frame;
    }


    public static void main(String[] args) {
        ScanQrPage scanQrPage = new ScanQrPage();

        scanQrPage.scanQrPageByBox();
    }


}


