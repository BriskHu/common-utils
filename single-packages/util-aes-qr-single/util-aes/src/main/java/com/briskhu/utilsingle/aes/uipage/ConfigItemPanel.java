package com.briskhu.utilsingle.aes.uipage;

import com.briskhu.common.jgui.operation.Button;
import com.briskhu.common.jgui.operation.Label;
import com.briskhu.common.jgui.operation.Panel;
import com.briskhu.common.jgui.operation.TextField;
import com.briskhu.utilsingle.aesqrcommon.model.AesConfigDto;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aes配置条目面板<p/>
 *
 * @author Brisk Hu
 * created on 2019-12-06
 **/
@Data
public class ConfigItemPanel extends JPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigItemPanel.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private JPanel configItemPanel = null;
    private int normalFontSize = 20;
    private int elementGap1 = 2;
    private int elementGap2 = 5;
    private int elementGap3 = 2;
    private int labelWidth = 80;
    private int labelHeight = 40;

    private AesEncryptPage aesEncryptPage = null;
    private AesDecryptPage aesDecryptPage = null;

    private String itemName = null;
    private JLabel itemNameLable = null;
    private String AES_MODE_HINT = "Aes模式";
    private JLabel aesModeLabel = null;
    private JTextField aesModeField = null;
    private String OFFSET_HINT = "偏移量  ";
    private JLabel offsetLabel = null;
    private JTextField offsetField = null;

    private String KEY_MODE_HINT = "秘钥形式";
    private JLabel keyModeLabel = null;
    private JTextField keyModeField = null;
    private String KEY_HINT = "Aes秘钥";
    private JLabel keyLabel = null;
    private JTextField keyField = null;

    private String APPLY_HINT = "应用";
    private JButton applyBtn = null;
    private AesConfigDto aesConfigDto = null;


    /* ---------------------------------------- methods ---------------------------------------- */
    public ConfigItemPanel(AesConfigDto aesConfigDto) {
        this.aesConfigDto = aesConfigDto;
//        this.configItemPanel = createConfigItemPanel(aesConfigDto);
    }

    /**
     * 创建Aes配置条目面板
     *
     * @return
     */
    public JPanel createConfigItemPanel(Dimension size) {
        return createConfigItemPanel(this.aesConfigDto, size);
    }

    /**
     * 创建Aes配置条目面板
     *
     * @param aesConfigDto
     * @param size
     * @return
     */
    public JPanel createConfigItemPanel(AesConfigDto aesConfigDto, Dimension size) {
        JPanel result = Panel.init(aesConfigDto.getId(), size);
        initComponents(aesConfigDto);
        addComponents(result);

        return result;
    }

    private void initComponents(AesConfigDto aesConfigDto) {
        itemNameLable = Label.init(aesConfigDto.getConfigName(), "itemNameLable", normalFontSize);
        itemNameLable.setPreferredSize(new Dimension(labelWidth-5, labelWidth));
        aesModeLabel = Label.init(AES_MODE_HINT, "aesModeLabel", normalFontSize);
        aesModeLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        aesModeField = TextField.init("aesModelField", 8, normalFontSize, aesConfigDto.getAesMode());
        offsetLabel = Label.init(OFFSET_HINT, "offsetLabel", normalFontSize);
        offsetLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        offsetField = TextField.init("offsetField", 13, normalFontSize, aesConfigDto.getOffset());

        keyModeLabel = Label.init(KEY_MODE_HINT, "keyModeLabel", normalFontSize);
        keyModeLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        keyModeField = TextField.init("keyModeField", 8, normalFontSize, aesConfigDto.getKeyMode());
        keyLabel = Label.init(KEY_HINT, "keyLabel", normalFontSize);
        keyLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        keyField = TextField.init("keyField", 13, normalFontSize, aesConfigDto.getKey());

        applyBtn = Button.init("applyBtn", APPLY_HINT, normalFontSize, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyAesConfig(aesConfigDto);
            }
        });
        applyBtn.setPreferredSize(new Dimension(labelWidth-5, labelWidth));
    }

    private void addComponents(JPanel panel) {
        Box container = Box.createHorizontalBox();
        Box colBox = Box.createVerticalBox();
        JPanel rowPane1 = new JPanel();
        Box rowBox1 = Box.createHorizontalBox();
        JPanel rowPane2 = new JPanel();
        Box rowBox2 = Box.createHorizontalBox();

        rowBox1.add(aesModeLabel);
        rowBox1.add(Box.createHorizontalStrut(elementGap1));
        rowBox1.add(aesModeField);
        rowBox1.add(Box.createHorizontalStrut(elementGap2));
        rowBox1.add(offsetLabel);
        rowBox1.add(Box.createHorizontalStrut(elementGap1));
        rowBox1.add(offsetField);
        rowPane1.add(rowBox1);

        rowBox2.add(keyModeLabel);
        rowBox2.add(Box.createHorizontalStrut(elementGap1));
        rowBox2.add(keyModeField);
        rowBox2.add(Box.createHorizontalStrut(elementGap2));
        rowBox2.add(keyLabel);
        rowBox2.add(Box.createHorizontalStrut(elementGap1));
        rowBox2.add(keyField);
        rowPane2.add(rowBox2);

        colBox.add(rowPane1);
        colBox.add(Box.createVerticalStrut(elementGap3));
        colBox.add(rowPane2);

        container.add(itemNameLable);
        container.add(Box.createHorizontalStrut(elementGap1));
        container.add(colBox);
        container.add(Box.createHorizontalStrut(elementGap2));
        container.add(applyBtn);

        panel.add(container);
    }


    /**
     * 将该按钮所对应的Aes配置应用到本工具的Aes加解密页面
     *
     * @param aesConfigDto
     */
    public void applyAesConfig(AesConfigDto aesConfigDto) {
        LOGGER.info("[applyAesConfig] aesConfigDto = {}", aesConfigDto);
        if (aesEncryptPage != null && aesDecryptPage != null) {
            aesEncryptPage.setAesConfigDto(aesConfigDto);
            aesDecryptPage.setAesConfigDto(aesConfigDto);
        } else {
            LOGGER.warn("[applyAesConfig] aesEncryptPage or aesDecryptPage is null, so do nothing.");
        }
    }


}


