package com.briskhu.common.jgui.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * YML文件解析工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-12
 **/
public class YmlFileProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(YmlFileProcessor.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */



    /* -------------------- parse yaml -------------------- */

    /**
     * 将yml文件读取到对应的模型实体中
     *
     * @param ymlFileName
     * @param ymlModelClass
     * @return
     */
    public static Object ymlToBean(FileInputStream ymlFileName, Class ymlModelClass) {
        Yaml ymlLoader = new Yaml();
        Object result = ymlLoader.loadAs(ymlFileName, ymlModelClass);
        LOGGER.debug("[ymlToBean] result = {}", result);

        return result;
    }

    /**
     * 将yml文件读取到对应的模型实体中
     *
     * @param ymlFileName
     * @param ymlModelClass
     * @return
     */
    public static Object itemToBean(String ymlFileName, Class ymlModelClass) {
        Yaml ymlLoader = new Yaml();
        Object result = ymlLoader.loadAs(ymlFileName, ymlModelClass);
        LOGGER.debug("[ymlToBean] result = {}", result);

        return result;
    }


    /* -------------------- preprocess -------------------- */
    /**
     * 获取一个不改变类字段定义顺序的Yaml处理类
     *
     * @return
     */
    public static Yaml getFieldsOrderKeptYaml() {
        Representer representer = new Representer();
        representer.setPropertyUtils(new UnsortedPropertyUtils());
        return new Yaml(representer);
    }


    private static class UnsortedPropertyUtils extends PropertyUtils {
        @Override
        protected Set<Property> createPropertySet(Class<? extends Object> type, BeanAccess bAccess) {
            Set<Property> result = new LinkedHashSet<Property>(getPropertiesMap(type,
                    BeanAccess.FIELD).values());
//            result.remove(result.iterator().next());// drop 'listInt' property
            return result;
        }
    }


    /* -------------------- save into yaml -------------------- */

    /**
     * 通过yaml文件对应的bean对象创建yml文件。
     *
     * @param filename
     * @param ymlBean
     * @return
     */
    public static boolean createYmlFile(String filename, Object ymlBean) {
        LOGGER.debug("[createYmlFile] start : filename = {}, ymlBean = {}", filename, ymlBean);
        boolean result = false;
        FileWriter ymlFile = null;
        try {
            ymlFile = new FileWriter(filename);
            Yaml ymlProcessor = getFieldsOrderKeptYaml();
            ymlProcessor.dump(ymlBean, ymlFile);
            result = true;
        } catch (IOException e) {
            LOGGER.error("[createYmlFile] 创建yml文件出错: {}", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据配置项创建yml文件
     *
     * @param filename
     * @param confItem
     * @return
     */
    public static boolean createYmlFile(String filename, String confItem) {
        File ymlFile = new File(filename);

        return false;
    }

    /**
     * 根据配置列表创建yml文件
     *
     * @param filename
     * @param confItemList
     * @return
     */
    public static boolean createYmlFile(String filename, List<String> confItemList) {

        return false;
    }


    /**
     * 将当个配置项添加到yml文件中
     *
     * @param filename
     * @param confItem
     * @return
     */
    public static boolean addItemToYmlFile(String filename, String confItem) {
        return false;
    }


}


