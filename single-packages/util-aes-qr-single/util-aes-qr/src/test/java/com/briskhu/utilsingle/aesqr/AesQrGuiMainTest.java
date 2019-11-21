package com.briskhu.utilsingle.aesqr;

import com.briskhu.common.jgui.other.YmlFileProcessor;
import com.briskhu.utilsingle.aesqrcommon.model.ApplicationConfigModel;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-12
 **/
public class AesQrGuiMainTest {

    @Test
    public void testYmlToBean() {
        YmlFileProcessor.itemToBean("application.yml", ApplicationConfigModel.class);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File ymlFile = new File(System.getProperty("user.dir") + "/conf/aes-qr/application.yml");
        YmlFileProcessor.ymlToBean(new FileInputStream(ymlFile), ApplicationConfigModel.class);
//        File ymlFile = new File(Object.class.getClassLoader().getResource("application.yml").getFile());
        System.out.println(Object.class.getResource("/"));
        File file = new File("application.yml");
        FileInputStream inputStream = new FileInputStream(file);
        YmlFileProcessor.ymlToBean(inputStream, ApplicationConfigModel.class);
    }

}