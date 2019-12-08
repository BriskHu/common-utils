package com.briskhu.utilsingle.aes.uipage;

import com.briskhu.common.jgui.other.YmlFileProcessor;
import com.briskhu.utilsingle.aes.constant.AesModeEnum;
import com.briskhu.utilsingle.aes.constant.KeyEncodingMode;
import com.briskhu.utilsingle.aesqrcommon.model.AesConfigDto;
import com.briskhu.utilsingle.aesqrcommon.model.ApplicationConfigModel;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.briskhu.common.jgui.operation.Frame;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-15
 **/
public class CodebookPageTest {

    @Test
    public void createCodebookPagePanel() {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setLineBreak(DumperOptions.LineBreak.UNIX);
        dumperOptions.setPrettyFlow(true);
        Yaml ymlProcessor = new Yaml(dumperOptions);

        ApplicationConfigModel applicationConfigModel = new ApplicationConfigModel();
        applicationConfigModel.setPassword("test123");
        String ymlStr1 = ymlProcessor.dump(applicationConfigModel);
        System.out.println("ymlStr1\n" + ymlStr1);


        Yaml yml2 = YmlFileProcessor.getFieldsOrderKeptYaml();
        applicationConfigModel.setPassword("test123");
        LinkedHashMap<String, String> aesCorelative1 = new LinkedHashMap<>();
        aesCorelative1.put("test1", "1");
        aesCorelative1.put("Abyss", "2");
        applicationConfigModel.setAesCorelativeList(aesCorelative1);
        String ymlStr2 = yml2.dumpAsMap(applicationConfigModel);
        System.out.println("ymlStr2\n" + ymlStr2);
    }


    public static void createConfigListPanel() {
        CodebookPage codebookPage = new CodebookPage();

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 900));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(codebookPage.createConfigListPanel(initAesConfigDto()));
        frame.setVisible(true);
    }

    private static List<AesConfigDto> initAesConfigDto(){
        List<AesConfigDto> aesConfigDtoList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            AesConfigDto aesConfigDto = new AesConfigDto();
            aesConfigDto.setId("" + i);
            aesConfigDto.setConfigName("test" + i);
            aesConfigDto.setAesMode(AesModeEnum.CBCPKCS7.getModeName());
            aesConfigDto.setOffset("012345678912345" + i);
            aesConfigDto.setKeyMode(KeyEncodingMode.PLATIN.getEncodingName());
            aesConfigDto.setKey("0123456789ABCDE" + i);
            aesConfigDtoList.add(aesConfigDto);
        }
        return aesConfigDtoList;
    }

    public static void createCodebookNormalPanel() {
        CodebookPage codebookPage = new CodebookPage();

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(800, 900));
        frame.setLocationRelativeTo(null);
        frame.setContentPane(codebookPage.createCodebookNormalPanel(initAesConfigDto()));
        frame.setVisible(true);
    }


    public static void main(String[] args) {
//        CodebookPageTest.createConfigListPanel();
        CodebookPageTest.createCodebookNormalPanel();
    }
}