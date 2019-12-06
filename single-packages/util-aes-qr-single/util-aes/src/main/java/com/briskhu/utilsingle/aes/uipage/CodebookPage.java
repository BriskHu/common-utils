package com.briskhu.utilsingle.aes.uipage;


import com.briskhu.common.jgui.operation.ComboBox;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import com.briskhu.common.jgui.other.GuiDebugTools;
import com.briskhu.common.jgui.other.YmlFileProcessor;
import com.briskhu.utilsingle.aes.AesGuiMain;
import com.briskhu.utilsingle.aes.algorithm.AESCoder;
import com.briskhu.utilsingle.aes.algorithm.AESUtilP7;
import com.briskhu.utilsingle.aes.algorithm.AesUtil;
import com.briskhu.utilsingle.aes.constant.AesModeEnum;
import com.briskhu.utilsingle.aes.constant.KeyEncodingMode;
import com.briskhu.utilsingle.aesqrcommon.model.AesConfigDto;
import com.briskhu.utilsingle.aesqrcommon.model.ApplicationConfigModel;
import javafx.scene.layout.Pane;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.briskhu.common.jgui.operation.TextArea;
import com.briskhu.common.jgui.operation.TextField;
import com.briskhu.common.jgui.operation.Button;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Aes密码本操作界面<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-12
 **/
public class CodebookPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodebookPage.class);

    private String aesMode = null;

    private JFrame jFrame = null;
    private final String FRAME_TITLE = "AES密码本";
    private int frameWidth = 800;
    private int frameHeight = 800;
    private JPanel codebookPanel = null;
    private int textBarWidth = 750;
    private int textBarHeight = 50;
    private int elementGap = 10;
    private int elementGap2 = 5;
    private int panelGap = 10;
    private JPanel[] rowPanels = null;
    /**
     * 正常字体尺寸
     */
    private final int FSIZE_NORMAL = 20;

    private JLabel blankCodebookLabel = null;
    private String NO_CODEBOOK_HINT = "尚未创建你的专属密码本";
    private JButton createCodebookBtn = null;
    private String CREATE_CODEBOOK = "创建密码本";
    private String ADD_CODEBOOK_PWD = "为密码本添加访问密码";
    private String INPUT_CODEBOOK_PWD = "请输入密码本的访问密码";
    private String NO_CODEBOOK_PWD = "密码本访问密码不能为空";
    private String codebookPassword = null;
    private String confFilename = null;


    private JPanel row1Panel = null;
    /**
     * AES解密方式
     */
    private JLabel aesCorelativeLabel = null;
    private String AES_CORELATIVE_HINT = "选择配置";
    private JComboBox<String> aesCorelativeCombo = null;
    private JLabel aesCorelativeNameLabel = null;
    private String AES_CORELATIVE_NAME_HINT = "配置名称";
    private String AES_CONFIG_NAME_REPEAT = "配置名称重复";

    private JPanel row2Panel = null;
    private JLabel aesCorelativeDescLabel = null;
    private String AES_CORELATIVE_DESC_HINT = "配置描述";
    private JTextField aesCorelativeDescField = null;

    private JPanel row3Panel = null;
    private JLabel aesModeLabel = null;
    private String AES_MODE_HINT = "Aes模式";
    private JTextField aesModeField = null;

    private JPanel row4Panel = null;
    private JLabel offsetLabel = null;
    private String OFFSET_HINT = "偏移量";
    private JTextField offsetField = null;

    private JPanel row5Panel = null;
    private JLabel keyLabel = null;
    private String KEY_HINT = "Aes秘钥";
    private JTextField keyField = null;

    private JPanel row6Panel = null;
    private JLabel keyModeLabel = null;
    private String KEY_MODE_HINT = "秘钥形式";
    private JTextField keyModeField = null;


    private String APPLY_AES_CONFIG_HINT = "使用该Aes配置";

    private JButton addAesConfigBtn = null;
    private String ADD_AES_CONFIG_HINT = "增加一条Aes配置";



    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 创建空白密码本界面
     *
     * @return
     */
    public JPanel createBlankCodebookPage() {
        codebookPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        codebookPanel.setLayout(layout);


        blankCodebookLabel = Label.init(NO_CODEBOOK_HINT, "blankCodebookLabel", FSIZE_NORMAL);
        createCodebookBtn = Button.init("createCodebookBtn", CREATE_CODEBOOK, FSIZE_NORMAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codebookPassword = JOptionPane.showInputDialog(codebookPanel, ADD_CODEBOOK_PWD, FRAME_TITLE, JOptionPane.PLAIN_MESSAGE);
                confFilename = System.getProperty("user.dir") + "/conf/aes-qr/application.yml";
                createCodebookConfigFile(confFilename, codebookPassword);
            }
        });

        Box box = Box.createHorizontalBox();
        box.add(blankCodebookLabel);
        box.add(Box.createHorizontalStrut(elementGap * 2));
        box.add(createCodebookBtn);
        box.setPreferredSize(new Dimension(300, textBarHeight));

        codebookPanel.add(box, BorderLayout.NORTH);

        return codebookPanel;
    }

    /**
     * 创建密码本配置文件
     *
     * @param confFilename
     * @param codebookPassword
     */
    private void createCodebookConfigFile(String confFilename, String codebookPassword) {
        ApplicationConfigModel applicationConfigModel = new ApplicationConfigModel();
        applicationConfigModel.setPassword(codebookPassword);

        YmlFileProcessor.createYmlFile(confFilename, applicationConfigModel);
    }

    /**
     *
     * @param aesConfigDtoList
     * @return
     */
    public JPanel createCodebookNormalPanel(List<AesConfigDto> aesConfigDtoList){
        codebookPanel = new JPanel();
        Box box = Box.createVerticalBox();
        addAesConfigBtn = Button.init("addAesConfigBtn", ADD_AES_CONFIG_HINT, FSIZE_NORMAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("[actionPerformed] ");
            }
        });
        box.add(addAesConfigBtn);
        box.add(Box.createVerticalStrut(elementGap*2));
        box.add(createConfigListPanel(aesConfigDtoList));
        codebookPanel.add(box);

        return codebookPanel;
    }

    /**
     * 创建Aes配置列表面板
     * @param aesConfigDtoList
     * @return
     */
    public JPanel createConfigListPanel(List<AesConfigDto> aesConfigDtoList) {
        JPanel result = new JPanel();

        Box box = Box.createVerticalBox();
        for (int i = 0; i < aesConfigDtoList.size() - 1; i++) {
            ConfigItemPanel configItemPanel1 = new ConfigItemPanel(aesConfigDtoList.get(i));
            box.add(configItemPanel1.createConfigItemPanel(new Dimension(700, 110)));
            box.add(Box.createVerticalStrut(elementGap));
        }
        ConfigItemPanel configItemPanel2 = new ConfigItemPanel(aesConfigDtoList.get(aesConfigDtoList.size() - 1));
        box.add(configItemPanel2.createConfigItemPanel(new Dimension(700, 110)));
        JPanel configListPanel = Panel.initForBox("configListPanel", frameWidth, frameHeight, box);

        JScrollPane scrollPane = new JScrollPane(configListPanel);
        scrollPane.setPreferredSize(new Dimension(frameWidth - 30, frameHeight - 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        result.add(scrollPane);

        return result;
    }


    /**
     * 增加Aes加密信息配置的操作页面
     */
    private void addAesCorelativeConfigPage() {

    }


    /**
     * 生成密码本页面的面板
     *
     * @return
     */
//    public JPanel createCodebookPagePanel() {
//        panel = new JPanel(null);
//
//        row1Panel = createRow1Panel("row1Panel");
//        row2Panel = createRow2Panel("row2Panel");
//        row3Panel = createRow3Panel("row3Panel");
//        row4Panel = createRow4Panel("row4Panel");
//        rowPanels = new JPanel[]{row1Panel, row2Panel, row3Panel, row4Panel, row5Panel, row6Panel};
//
//        LOGGER.debug("[createAesDecryptPagePanel] panel: (x,y)=({},{})", panel.getX(), panel.getY());
//        row1Panel.setLocation(panel.getX() + panelGap, panel.getY() + panelGap);
//        row1Panel.setSize(textBarWidth, textBarHeight);
//
//        row2Panel.setLocation(10, textBarHeight + panelGap * 2);
//        row2Panel.setSize(120, textBarHeight);
//        row3Panel.setLocation(130, textBarHeight + panelGap * 2);
//        row3Panel.setSize(originAreaWidth, originAreaHeight);
//
//        row4Panel.setLocation(10, textBarHeight + originAreaHeight + panelGap * 3);
//        row4Panel.setSize(textBarWidth, textBarHeight);
//
//        row5Panel.setLocation(10, textBarHeight * 2 + originAreaHeight + panelGap * 4);
//        row5Panel.setSize(120, textBarHeight);
//        row6Panel.setLocation(130, textBarHeight * 2 + originAreaHeight + panelGap * 4);
//        row6Panel.setSize(decryptAreaWidth, decryptAreaHeight);
//
//        GuiDebugTools.printBorderByToggle(Color.GREEN, rowPanels);
//        LOGGER.debug("[createAesDecryptPagePanel] panel: w = {}, h = {}", panel.getWidth(), panel.getHeight());
//        Panel.add(panel, rowPanels);
//
//        return panel;
//    }
//    private JPanel createRow1Panel(String panelName) {
//        aesModeLabel = Label.init(MODE_HINT, FSIZE_NORMAL);
//        String[] aesModes = new String[]{AesModeEnum.ALL_DEFAULT.getModeName(), AesModeEnum.CBCPKCS7.getModeName()};
//
//
//        private JPanel row1Panel = null;
//        /**
//         * AES解密方式
//         */
//        private JLabel aesCorelativeLabel = null;
//        private String AES_CORELATIVE_HINT = "选择配置";
//        private JComboBox<String> aesCorelativeCombo = null;
//        private JLabel aesCorelativeNameLabel = null;
//        private String AES_CORELATIVE_NAME_HINT = "配置名称";
//        private String AES_CONFIG_NAME_REPEAT = "配置名称重复";
//
//
//        aesCorelativeCombo = ComboBox.initForString("imgFormat", FSIZE_NORMAL, new Dimension(190, 10),
//                aesModes, new ItemListener() {
//                    @Override
//                    public void itemStateChanged(ItemEvent e) {
//                        aesMode = (String) aesCorelativeCombo.getSelectedItem();
//                    }
//                });
//        aesCorelativeCombo.setSelectedIndex(0);
//        aesMode = aesModes[0];
//
//        offsetLabel = Label.init(OFFSET_HINT, FSIZE_NORMAL);
//        offsetField = TextField.init("originText", 21, FSIZE_NORMAL, INPUT_OFFSET_HINT);
//
//        Box box = Box.createHorizontalBox();
//        box.add(aesModeLabel);
//        box.add(Box.createHorizontalStrut(elementGap));
//        box.add(aesCorelativeCombo);
//        box.add(Box.createHorizontalStrut(elementGap * 5));
//        box.add(offsetLabel);
//        box.add(Box.createHorizontalStrut(elementGap));
//        box.add(offsetField);
//
//        return Panel.initForBox(panelName, 200, 50, box);
//    }
//
//    private JPanel createRow2Panel(String panelName) {
//        aesCorelativeNameLabel = Label.init(AES_CORELATIVE_NAME_HINT, FSIZE_NORMAL);
//        JPanel panel = Panel.init(panelName, 100, 10);
//        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        panel.add(aesCorelativeNameLabel);
//
//        return panel;
//    }
//
//    private JPanel createRow3Panel(String panelName) {
//        originArea = TextArea.init(INPUT_ORIGIN_HINT, "originArea", FSIZE_NORMAL);
//        originArea.setPreferredSize(new Dimension(originAreaWidth, originAreaHeight));
//        originArea.enableInputMethods(true);
//
//        JPanel panel = Panel.init(panelName, originAreaWidth, originAreaHeight);
//        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
////        panel.add(originArea);
//        JScrollPane scrollPane = TextArea.putIntoScrollbar(originArea, new Dimension(originAreaWidth-5, originAreaHeight-5));
//        panel.add(scrollPane);
//
//        return panel;
//    }
//
//    private JPanel createRow4Panel(String panelName) {
//        offsetLabel = Label.init(OFFSET_HINT, FSIZE_NORMAL);
//        offsetField = TextField.init("offsetField", 22, FSIZE_NORMAL, INPUT_KEY_HINT);
//        decryptBtn = Button.init("decryptBtn", DECRYPT_BTN_HINT, FSIZE_NORMAL, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                LOGGER.debug("[actionPerformed] Button {} has been pressed", decryptBtn.getName());
//                doDecryptBtn();
//            }
//        });
//        decryptBtn.setPreferredSize(new Dimension(120, 40));
//
//        String[] keyEncodingModes = new String[]{KeyEncodingMode.PLATIN.getEncodingName(), KeyEncodingMode.BASE64.getEncodingName()};
//        keyEncodingModeComb = ComboBox.initForString("keyEncodingModeComb", FSIZE_NORMAL, new Dimension(113, 10),
//                keyEncodingModes, new ItemListener() {
//                    @Override
//                    public void itemStateChanged(ItemEvent e) {
//                        keyEncodingMode = (String) keyEncodingModeComb.getSelectedItem();
//                    }
//                });
//        keyEncodingModeComb.setSelectedIndex(0);
//        keyEncodingMode = keyEncodingModes[0];
//
//        Box box = Box.createHorizontalBox();
//        box.add(offsetLabel);
//        box.add(Box.createHorizontalStrut(elementGap));
//        box.add(offsetField);
//        box.add(Box.createHorizontalStrut(elementGap));
//        box.add(keyEncodingModeComb);
//        box.add(Box.createHorizontalStrut(elementGap * 5));
//        box.add(decryptBtn);
//
//        return Panel.initForBox(panelName, 180, 10, box);
//    }
//
//    private JPanel createRow5Panel(String panelName) {
//        keyLabel = Label.init(KEY_HINT, FSIZE_NORMAL);
//        JPanel panel = Panel.init(panelName, 100, 10);
//        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        panel.add(keyLabel);
//
//        return panel;
//    }
//
//    private JPanel createRow6Panel(String panelName) {
//        decryptArea = TextArea.init("null", "decryptArea", FSIZE_NORMAL);
//        decryptArea.setPreferredSize(new Dimension(decryptAreaWidth, decryptAreaHeight));
//        decryptArea.enableInputMethods(false);
//
//        JPanel panel = Panel.init(panelName, decryptAreaWidth, decryptAreaHeight);
//        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        JScrollPane scrollPane = TextArea.putIntoScrollbar(decryptArea, new Dimension(decryptAreaWidth-5, decryptAreaHeight-5));
//        panel.add(scrollPane);
//
//        return panel;
//    }
//
//    public void doDecryptBtn() {
//        decryptResult = null;
//        decryptArea.setText("null");
//        decryptArea.validate();
//        if (checkParams()) {
//            try{
//                if (aesMode.equals(AesModeEnum.ALL_DEFAULT.getModeName())) {
//                    if (keyEncodingMode.equals(KeyEncodingMode.PLATIN.getEncodingName())) {
//                        decryptResult = new String(AESCoder.decrypt(Base64.decodeBase64(originText), key.getBytes()));
//                    } else {
//                        decryptResult = new String(AESCoder.decrypt(Base64.decodeBase64(originText), key));
//                    }
//                } else if (aesMode.equals(AesModeEnum.CBCPKCS7.getModeName())) {
//                    AESUtilP7.setOffset(offset);
//                    decryptResult = AESUtilP7.decrypt(originText, key);
//                }
//                LOGGER.info("[doDecryptBtn] decryptResult = {}", decryptResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//                LOGGER.error("[doDecryptBtn] 解密出错\n");
//                decryptResult = null;
//            }
//
//            decryptArea.setText(decryptResult);
//            if (decryptResult == null){
//                JOptionPane.showMessageDialog(panel, FAILED_DECRYPT, AesGuiMain.AES_DE_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
//                decryptArea.setText(FAILED_DECRYPT);
//            }
//            decryptArea.validate();
//        }
//    }
//
//    private boolean checkParams() {
//        boolean result = false;
//        originText = originArea.getText();
//        key = offsetField.getText();
//        offset = offsetField.getText();
//        LOGGER.debug("[checkParams] originText = {}, key = {}, offset = {}, keyEncodingMode = {}", originText, key, offset, keyEncodingMode);
//
//        if (originText.equals("")) {
//            LOGGER.error("[checkParams] originText or key is null");
//            JOptionPane.showMessageDialog(panel, NO_ORIGIN, AesGuiMain.AES_DE_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
//            originArea.setText(INPUT_ORIGIN_HINT);
//            originArea.validate();
//            return false;
//        }
//        if (key.equals("")) {
//            LOGGER.error("[checkParams] key is null");
//            JOptionPane.showMessageDialog(panel, NO_KEY, AesGuiMain.AES_DE_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
//            offsetField.setText(INPUT_KEY_HINT);
//            offsetField.validate();
//            return false;
//        }
//
//        byte[] keyBytes = null;
//        if (keyEncodingMode.equals(KeyEncodingMode.BASE64.getEncodingName())) {
//            keyBytes = Base64.decodeBase64(key);
//        } else {
//            keyBytes = key.getBytes();
//        }
//        if (!AesUtil.isKeySizeValid(keyBytes.length)) {
//            LOGGER.error("[checkParams] The length of key is illegal.");
//            JOptionPane.showMessageDialog(panel, ILLEGAL_KEY_LENGTH, AesGuiMain.AES_DE_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
//            offsetField.setText(INPUT_KEY_HINT);
//            offsetField.validate();
//            return false;
//        }
//
//        result = true;
//
//        return result;
//    }
    public void setJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }


}


