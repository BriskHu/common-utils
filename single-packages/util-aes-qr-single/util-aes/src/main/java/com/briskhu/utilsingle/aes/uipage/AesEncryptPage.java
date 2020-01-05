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
import com.briskhu.utilsingle.aesqrcommon.model.AesConfigDto;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Aes加密操作界面<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-09
 **/
@Data
public class AesEncryptPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesEncryptPage.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private AesConfigDto aesConfigDto = null;
    private String aesMode = null;
    private static final Charset DATA_CHARSET = StandardCharsets.UTF_8;

    private JFrame jFrame = null;
    private final String FRAME_TITLE = "AES加密";
    private int frameWidth = 800;
    private int frameHeight = 800;
    private JPanel panel = null;
    private int textBarWidth = 750;
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
    private String INPUT_OFFSET_HINT = "请输入16位的偏移量";
    private String offset = null;
    private String NO_OFFSET = "偏移量为空";

    private JPanel row2Panel = null;
    private JLabel originLabel = null;
    private String ORIGIN_HINT = "待加密字符";

    private JPanel row3Panel = null;
    private JTextArea originArea = null;
    private String originText = null;
    private int originAreaWidth = 625;
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
    private int encryptAreaWidth = 625;
    private int encryptAreaHeight = 280;
    private String encryptResult = null;
    private String FAILED_ENCRYPT = "加密失败，请检查加密秘钥、偏移量是否正确。";

    private JPanel row7Panel = null;
    private JButton getTimestampBtn = null;
    private String GET_TIMISTAMP_HINT = "获取时间戳";
    private JTextField timestampField = null;
    private JButton getDateBtn = null;
    private String GET_DATE_HINT = "转换为日期";
    private String TIME_FORMAT_ERROR = "时间戳格式错误";
    private String TIME_LENGTH_ERROR = "时间戳长度错误";
    private JLabel dateLabel = null;
    private long nowTimestamp = 0;

    private JPanel authorPanel = null;
    private JLabel authorLabel = null;
    private JLabel versionLabel = null;


    /* ---------------------------------------- methods ---------------------------------------- */
    public void setAesConfigDto(AesConfigDto aesConfigDto) {
        this.aesConfigDto = aesConfigDto;
        if (this.panel != null) {
            this.aesModeCombo.setSelectedItem(aesConfigDto.getAesMode());
            this.offsetField.setText(aesConfigDto.getOffset());
            this.keyEncodingModeComb.setSelectedItem(aesConfigDto.getKeyMode());
            this.keyField.setText(aesConfigDto.getKey());
            this.panel.validate();
        }
    }

    /**
     * 生成Aes加密页面的面板
     *
     * @return
     */
    public JPanel createAesEncryptPagePanel() {
        LOGGER.info("[createAesEncryptPagePanel] start...");
//        GuiDebugTools.setPrintBorderToggle(true);
        panel = new JPanel(null);

        row1Panel = createRow1Panel("row1Panel");
        row2Panel = createRow2Panel("row2Panel");
        row3Panel = createRow3Panel("row3Panel");
        row4Panel = createRow4Panel("row4Panel");
        row5Panel = createRow5Panel("row5Panel");
        row6Panel = createRow6Panel("row6Panel");
        row7Panel = createRow7Panel("row7Panel");
        authorPanel = createAuthorPanel("authorPanel");
        rowPanels = new JPanel[]{row1Panel, row2Panel, row3Panel, row4Panel, row5Panel,
                row6Panel, row7Panel, authorPanel};

        LOGGER.debug("[createAesEncryptPagePanel] panel: (x,y)=({},{})", panel.getX(), panel.getY());
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

        row7Panel.setLocation(10, textBarHeight * 2 + originAreaHeight * 2 + panelGap * 5);
        row7Panel.setSize(frameWidth, textBarHeight);

        authorPanel.setSize(textBarWidth, textBarHeight);
        authorPanel.setLocation(10, frameHeight - 20);

        GuiDebugTools.printBorderByToggle(Color.GREEN, rowPanels);
        LOGGER.debug("[createAesEncryptPagePanel] panel: w = {}, h = {}", panel.getWidth(), panel.getHeight());
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
        offsetField = TextField.init("originText", 21, FSIZE_NORMAL, INPUT_OFFSET_HINT);

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
        originArea.setPreferredSize(new Dimension(originAreaWidth, originAreaHeight));
        originArea.enableInputMethods(true);

        JPanel panel = Panel.init(panelName, originAreaWidth, originAreaHeight);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        panel.add(originArea);
        JScrollPane scrollPane = TextArea.putIntoScrollbar(originArea, new Dimension(originAreaWidth - 5, originAreaHeight - 5));
        panel.add(scrollPane);

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
        keyEncodingModeComb = ComboBox.initForString("keyEncodingModeComb", FSIZE_NORMAL, new Dimension(113, 10),
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
        box.add(Box.createHorizontalStrut(elementGap * 5));
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
        JScrollPane scrollPane = TextArea.putIntoScrollbar(encryptArea, new Dimension(encryptAreaWidth - 5, encryptAreaHeight - 5));
        panel.add(scrollPane);

        return panel;
    }

    private JPanel createRow7Panel(String panelName) {
        getTimestampBtn = Button.init("getTimestampBtn", GET_TIMISTAMP_HINT, FSIZE_NORMAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.debug("[getTimestampBtn-actionPerformed] Button {} has been pressed", getTimestampBtn.getName());
                doGetTimeBtn();
            }
        });
        getTimestampBtn.setPreferredSize(new Dimension(135, 40));
        nowTimestamp = System.currentTimeMillis();
        timestampField = TextField.init("timestampField", 10, FSIZE_NORMAL, nowTimestamp + "");
        timestampField.setPreferredSize(new Dimension(150, 35));
        dateLabel = Label.init("", "dateLabel", FSIZE_NORMAL);
        dateLabel.setPreferredSize(new Dimension(260, 35));
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        getDateBtn = Button.init("getDateBtn", GET_DATE_HINT, FSIZE_NORMAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timestampField.getText() != null) {
                    String timeFieldStr = timestampField.getText();
                    LOGGER.debug("[getDateBtn-actionPerformed] timeFieldStr = {}", timeFieldStr);
                    if (!timeFieldStr.matches("^\\d+$")) {
                        LOGGER.error("[getDateBtn-actionPerformed] 时间戳格式错误");
                        JOptionPane.showMessageDialog(panel, TIME_FORMAT_ERROR, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Date date = null;
                    if (timeFieldStr.length() == 13) {
                        date = Date.from(Instant.ofEpochMilli(Long.parseLong(timeFieldStr)));
                    } else if (timeFieldStr.length() == 10) {
                        date = Date.from(Instant.ofEpochSecond(Long.parseLong(timeFieldStr)));
                    } else {
                        LOGGER.error("[getDateBtn-actionPerformed] 时间戳长度错误");
                        JOptionPane.showMessageDialog(panel, TIME_LENGTH_ERROR, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
                    dateLabel.setText(simpleDateFormat.format(date));
                }

            }
        });
        getDateBtn.setPreferredSize(new Dimension(135, 35));

        Box box = Box.createHorizontalBox();
        box.add(getTimestampBtn);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(timestampField);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(getDateBtn);
        box.add(Box.createHorizontalStrut(elementGap));
        box.add(dateLabel);

        return Panel.initForBox(panelName, 780, 10, box);
    }

    private JPanel createAuthorPanel(String panelName) {
        authorLabel = Label.init("Developer:  Brisk Hu", FSIZE_NORMAL);
        versionLabel = Label.init("Version:  1.0.0", FSIZE_NORMAL);

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(30));
        box.add(authorLabel);
        box.add(Box.createHorizontalStrut(80));
        box.add(versionLabel);
//        box.add(Box.createHorizontalGlue());
        JPanel bottomBarPanel = Panel.initForBox(panelName, 500, 30, box);

        GuiDebugTools.printBorderByToggle(Color.RED, authorLabel, versionLabel, box);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(bottomBarPanel);

        return panel;
    }

    public void doEncryptBtn() {
        encryptResult = null;

        if (checkParams()) {
            try {
                if (aesMode.equals(AesModeEnum.ALL_DEFAULT.getModeName())) {
                    if (keyEncodingMode.equals(KeyEncodingMode.PLATIN.getEncodingName())) {
                        encryptResult = new String(Base64.encodeBase64(AESCoder.encrypt(originText.getBytes(), key.getBytes())));
                    } else {
                        encryptResult = new String(Base64.encodeBase64(AESCoder.encrypt(originText, key)));
                    }
                } else if (aesMode.equals(AesModeEnum.CBCPKCS7.getModeName())) {
                    AESUtilP7.setOffset(offset);
                    if (keyEncodingMode.equals(KeyEncodingMode.PLATIN.getEncodingName())) {
                        encryptResult = AESUtilP7.encrypt(originText, key);
                    } else {
                        encryptResult = new String(Base64.encodeBase64(AESUtilP7.encrypt(originText.getBytes(DATA_CHARSET),
                                Base64.decodeBase64(key.getBytes(DATA_CHARSET)))));
                    }
                }
                LOGGER.info("[doEncryptBtn] encryptResult = {}\n", encryptResult);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("[doEncryptBtn] 加密出错\n");
                encryptResult = null;
            }

            encryptArea.setText(encryptResult);
            if (encryptResult == null) {
                JOptionPane.showMessageDialog(panel, FAILED_ENCRYPT, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                encryptArea.setText(FAILED_ENCRYPT);
            }
            encryptArea.validate();
        }
    }

    private boolean checkParams() {
        boolean result = false;
        originText = originArea.getText();
        key = keyField.getText();
        offset = offsetField.getText();
        LOGGER.debug("[checkParams] originText = {}, key = {}, offset = {}, keyEncodingMode = {}", originText, key, offset, keyEncodingMode);

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
//        if (offset.equals("")) {
//            LOGGER.error("[checkParams] offset is null");
//            JOptionPane.showMessageDialog(panel, NO_OFFSET, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
//            offsetField.setText(INPUT_OFFSET_HINT);
//            offsetField.validate();
//            return false;
//        }

        byte[] keyBytes = null;
        if (keyEncodingMode.equals(KeyEncodingMode.BASE64.getEncodingName())) {
            keyBytes = Base64.decodeBase64(key);
        } else {
            keyBytes = key.getBytes();
        }
        if (!AesUtil.isKeySizeValid(keyBytes.length)) {
            LOGGER.error("[checkParams] The length of key is illegal.");
            JOptionPane.showMessageDialog(panel, ILLEGAL_KEY_LENGTH, AesGuiMain.AES_EN_PAGE_TITLE, JOptionPane.ERROR_MESSAGE);
            keyField.setText(INPUT_KEY_HINT);
            keyField.validate();
            return false;
        }

        result = true;

        return result;
    }

    public void doGetTimeBtn() {
        timestampField.setText(System.currentTimeMillis() + "");
        timestampField.validate();
    }


    public void setJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }


}


