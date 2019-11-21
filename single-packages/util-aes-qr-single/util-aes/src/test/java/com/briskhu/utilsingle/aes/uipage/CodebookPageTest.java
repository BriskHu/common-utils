package com.briskhu.utilsingle.aes.uipage;

import com.briskhu.common.jgui.other.YmlFileProcessor;
import com.briskhu.utilsingle.aesqrcommon.model.ApplicationConfigModel;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.LinkedHashMap;


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
}