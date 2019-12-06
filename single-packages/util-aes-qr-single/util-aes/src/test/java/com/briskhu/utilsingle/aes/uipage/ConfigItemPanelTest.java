package com.briskhu.utilsingle.aes.uipage;

import com.briskhu.common.jgui.operation.Frame;
import com.briskhu.utilsingle.aes.constant.AesModeEnum;
import com.briskhu.utilsingle.aes.constant.KeyEncodingMode;
import com.briskhu.utilsingle.aesqrcommon.model.AesConfigDto;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-12-06
 **/
public class ConfigItemPanelTest {


    public static void createConfigItemPanel() {
        AesConfigDto aesConfigDto = new AesConfigDto();
        aesConfigDto.setId("1");
        aesConfigDto.setConfigName("test");
        aesConfigDto.setAesMode(AesModeEnum.CBCPKCS7.getModeName());
        aesConfigDto.setOffset("0123456789123456");
        aesConfigDto.setKeyMode(KeyEncodingMode.PLATIN.getEncodingName());
        aesConfigDto.setKey("0123456789ABCDEF");

        ConfigItemPanel configItemPanel = new ConfigItemPanel(aesConfigDto);
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 900));
        frame.setLocationRelativeTo(null);
        Frame.refresh(frame, configItemPanel.createConfigItemPanel(new Dimension(500, 200)));
    }

    public static void main(String[] args) {
        ConfigItemPanelTest.createConfigItemPanel();
    }

}