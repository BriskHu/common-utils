package com.briskhu.utilsingle.aes.uipage;

import com.briskhu.common.jgui.operation.*;
import com.briskhu.common.jgui.operation.Button;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import com.briskhu.common.jgui.operation.TextArea;
import com.briskhu.common.jgui.operation.TextField;
import com.briskhu.common.jgui.other.GuiDebugTools;
import com.briskhu.utilsingle.aes.AesGuiMain;
import com.briskhu.utilsingle.aes.algorithm.AESCoder;
import com.briskhu.utilsingle.aes.algorithm.AESUtilP7;
import com.briskhu.utilsingle.aes.algorithm.AesUtil;
import com.briskhu.utilsingle.aes.constant.AesModeEnum;
import com.briskhu.utilsingle.aes.constant.KeyEncodingMode;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.symmetric.Grainv1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Aes加密操作界面<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-09
 **/
public class AesEncryptPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesEncryptPage.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private String aesMode = null;

    private JFrame jFrame = null;
    private final String FRAME_TITLE = "AES加密";
    private int frameWidth = 800;
    private int frameHeight = 800;
    private JPanel panel = null;
    private int textBarWidth = 780;
    private int textBarHeight = 50;
    private int elementGap = 10;
    private int panelGap = 10;
    private JPanel[] rowPanels = null;
    /**
     * 正常字体尺寸
     */
    private final int FSIZE_NORMAL = 20;

    private JPanel row1Panel = null;
    /**
     * AES加密方式
     */
    private JLabel aesModeLabel = null;
    private String MODE_HINT = "加密方式";
    private JComboBox<String> aesModeCombo = null;
    private JLabel offsetLabel = null;
    private String OFFSET_HINT = "偏移量";
    private JTextField offsetField = null;
    private String INPUT_OFFSET_HINT = "请输入偏移量";
    private String offset = null;
    private String NO_OFFSET = "偏移量为空";

    private JPanel row2Panel = null;
    private JLabel originLabel = null;
    private String ORIGIN_HINT = "待加密字符";

    private JPanel row3Panel = null;
    private JTextArea originArea = null;
    private String originText = null;
    private int originAreaWidth = 610;
    private int originAreaHeight = 280;
    private String INPUT_ORIGIN_HINT = "请输入待加密内容";
    private String NO_ORIGIN = "待加密内容为空";

    private JPanel row4Panel = null;
    private JLabel keyLabel = null;
    private String KEY_HINT = "加密秘钥";
    private String key = null;
    private String NO_KEY = "加密秘钥为空";
    private JTextField keyField = null;
    private String INPUT_KEY_HINT = "请输入16/24/32位长度的加密秘钥";
    private JComboBox<String> keyEncodingModeComb = null;
    private String keyEncodingMode = null;
    private String ILLEGAL_KEY_LENGTH = "加密秘钥长度不合法";

    private JButton encryptBtn = null;
    private String ENCRYPT_BTN_HINT = "执行加密";

    private JPanel row5Panel = null;
    private JLabel encryptLabel = null;
    private String ENCRYPT_HINT = "加密后结果";

    private JPanel row6Panel = null;
    private JTextArea encryptArea = null;
    private int encryptAreaWidth = 610;
    private int encryptAreaHeight = 280;
    private String encryptResult = null;


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 生成二维码页面的面板
     *
     * @return
     */
    public JPanel createAesEncryptPagePanel() {
        panel = new JPanel(null);

        row1Panel = createRow1Panel("row1Panel");
        row2Panel = createRow2Panel("row2Panel");
        row3Panel = createRow3Panel("row3Panel");
        row4Panel = createRow4Panel("row4Panel");
        row5Panel = createRow5Panel("row5Panel");
        row6Panel = createRow6Panel("row6Panel");
        rowPanels = new JPanel[]{row1Panel, row2Panel, row3Panel, row4Panel, row5Panel, row6Panel};

        LOGGER.debug("[createAesEncryptPagePanel] resultPanel: (x,y)=({},{})", panel.getX(), panel.getY());
        row1Panel.setLocation(panel.getX() + panelGap, panel.getY() + panelGap);
        row1Panel.setSize(textBarWidth, textBarHeight);

        row2Panel.setLocation(10, textBarHeight + panelGap * 2);
        row2Panel.setSize(120, textBarHeight);
        row3Panel.setLocation(130, textBarHeight + panelGap * 2);
        row3Panel.setSize(originAreaWidth, originAreaHeight);

        row4Panel.setLocation(10, textBarHeight + originAreaHeight + panelGap * 3);
        row4Panel.setSize(textBarWidth, textBarHeight);

        row5Panel.setLocation(10, textBarHeight * 2 + originAreaHeight + panelGap * 4);
        row5Panel.setSize(120, textBarHeight);
        row6Panel.setLocation(130, textBarHeight * 2 + originAreaHeight + panelGap * 4);
        row6Panel.setSize(encryptAreaWidth, encryptAreaHeight);

        GuiDebugTools.printBorderByToggle(Color.GREEN, rowPanels);
        LOGGER.debug("[scanQrPagePanel] panel: w = {}, h = {}", panel.getWidth(), panel.getHeight());
        Panel.add(panel, rowPanels);

        return panel;
    }


    private JPanel createRow1Panel(String panelName) {
        aesModeLabel = Label.init(MODE_HINT, FSIZE_NORMAL);
        String[] aesModes = new String[]{AesModeEnum.ALL_DEFAULT.getModeName(), AesModeEnum.CBCPKCS7.getModeName()};
        aesModeCombo = ComboBox.initForString("imgFormat", FSIZE_NORMAL, new Dimension(190, 10),
                aesModes, new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        aesMode = (String) aesModeCombo.getSelectedItem();
                    }
                });
        aesModeCombo.setSelectedIndex(0);
        aesMode = aesModes[0];

        offsetLabel = Label.init(OFFSET_HINT, FSIZE_NORMAL);
        offsetField = TextField.init("originText", 20, FSIZE_NORMAL, INPUT_OFFSET_HINT);

        Box box = Box.createHorizontalBox();
        box.add(aesModeLabel);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(aesModeCombo);
        box.add(Box.createHorizontalStrut(elementGap * 5));
        box.add(offsetLabel);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(offsetField);

        return Panel.initForBox(panelName, 200, 50, box);
    }

    private JPanel createRow2Panel(String panelName) {
        originLabel = Label.init(ORIGIN_HINT, FSIZE_NORMAL);
        JPanel panel = Panel.init(panelName, 100, 10);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(originLabel);

        return panel;
    }

    private JPanel createRow3Panel(String panelName) {
        originArea = TextArea.init(INPUT_ORIGIN_HINT, "originArea", FSIZE_NORMAL);
        originArea.setPreferredSize(new Dimension(encryptAreaWidth, originAreaHeight));
        originArea.enableInputMethods(true);

        JPanel panel = Panel.init(panelName, encryptAreaWidth, encryptAreaHeight);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(originArea);

        return panel;
    }

    private JPanel createRow4Panel(String panelName) {
        keyLabel = Label.init(KEY_HINT, FSIZE_NORMAL);
        keyField = TextField.init("keyField", 22, FSIZE_NORMAL, INPUT_KEY_HINT);
        encryptBtn = Button.init("encryptBtn", ENCRYPT_BTN_HINT, FSIZE_NORMAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.debug("[actionPerformed] Button {} has been pressed", encryptBtn.getName());
                doEncryptBtn();
            }
        });
        encryptBtn.setPreferredSize(new Dimension(120, 40));

        String[] keyEncodingModes = new String[]{KeyEncodingMode.PLATIN.getEncodingName(), KeyEncodingMode.BASE64.getEncodingName()};
        keyEncodingModeComb = ComboBox.initForString("keyEncodingModeComb", FSIZE_NORMAL, new Dimension(110, 10),
                keyEncodingModes, new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        keyEncodingMode = (String) keyEncodingModeComb.getSelectedItem();
                    }
                });
        keyEncodingModeComb.setSelectedIndex(0);
        keyEncodingMode = keyEncodingModes[0];

        Box box = Box.createHorizontalBox();
        box.add(keyLabel);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(keyField);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(keyEncodingModeComb);
        box.add(Box.createHorizontalStrut(elementGap * 4));
        box.add(encryptBtn);

        return Panel.initForBox(panelName, 180, 10, box);
    }

    private JPanel createRow5Panel(String panelName) {
        encryptLabel = Label.init(ENCRYPT_HINT, FSIZE_NORMAL);
        JPanel panel = Panel.init(panelName, 100, 10);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(encryptLabel);

        return panel;
    }

    private JPanel createRow6Panel(String panelName) {
        encryptArea = TextArea.init("null", "encryptArea", FSIZE_NORMAL);
        encryptArea.setPreferredSize(new Dimension(encryptAreaWidth, encryptAreaHeight));
        encryptArea.enableInputMethods(false);

        JPanel panel = Panel.init(panelName, encryptAreaWidth, encryptAreaHeight);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(encryptArea);

        return panel;
    }

    public void doEncryptBtn() {
        if (checkParams()) {
            if (aesMode.equals(AesModeEnum.ALL_DEFAULT.getModeName())) {
                try {
                    if (keyEncodingMode.equals(KeyEncodingMode.PLATIN.getEncodingName())){
                        encryptResult = new String(Base64.encodeBase64(AESCoder.encrypt(originText.getBytes(), key.getBytes())));
                    }else {
                        encryptResult = new String(Base64.encodeBase64(AESCoder.encrypt(originText, key)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (aesMode.equals(AesModeEnum.CBCPKCS7.getModeName())) {
                AESUtilP7.setOffset(offset);
                encryptResult = AESUtilP7.encrypt(originText, key);
            }
            encryptArea.setText(encryptResult);
            encryptArea.validate();
        }
    }

    private boolean checkParams() {
        boolean result = false;
        originText = originArea.getText();
        key = keyField.getText();
        offset = offsetField.getText();
        LOGGER.debug("[checkParams] originText = {}, key = {}, offset = {}, keyEncodingMode= {}", originText, key, offset, keyEncodingMode);

        if (originText.equals("")) {
            LOGGER.error("[checkParams] originText or key is null");
            JOptionPane.showMessageDialog(panel, NO_ORIGIN, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
            originArea.setText(INPUT_ORIGIN_HINT);
            originArea.validate();
            return false;
        }
        if (key.equals("")) {
            LOGGER.error("[checkParams] key is null");
            JOptionPane.showMessageDialog(panel, NO_KEY, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
            keyField.setText(INPUT_KEY_HINT);
            keyField.validate();
            return false;
        }
        if (offset.equals("")){
            LOGGER.error("[checkParams] offset is null");
            JOptionPane.showMessageDialog(panel, NO_OFFSET, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
            offsetField.setText(INPUT_OFFSET_HINT);
            offsetField.validate();
            return false;
        }
        if (keyEncodingMode.equals(KeyEncodingMode.BASE64.getEncodingName())){
            byte[] keyBytes = Base64.decodeBase64(key);
            if (!AesUtil.isKeySizeValid(keyBytes.length)){
                LOGGER.error("[checkParams] The length of key is illegal.");
                JOptionPane.showMessageDialog(panel, ILLEGAL_KEY_LENGTH, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                keyField.setText(INPUT_KEY_HINT);
                keyField.validate();
                return false;
            }
        }

        result = true;

        return result;
    }

    public void setJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }


}


