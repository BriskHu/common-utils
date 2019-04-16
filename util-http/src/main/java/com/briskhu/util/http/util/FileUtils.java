package com.briskhu.util.http.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-03-25
 **/
@Component
public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 存储文件到指定路径
     * @param file
     * @param filePath
     * @param fileName
     * @return
     */
    public void storeFile(byte[] file, String filePath, String fileName){

        if(StringUtils.isEmpty(filePath)){
            throw new RuntimeException("文件路径不存在");
        }
        File targetFile=null;
        targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath+fileName);
            out.write(file);
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件没有找到");
        } catch (IOException e) {
            throw new RuntimeException("文件写入异常");
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件重命名
     * @param originalFileName
     * @return
     */
    public String renameFile(String originalFileName){
        String prefix=originalFileName.substring(0,originalFileName.lastIndexOf("."));
        String suffix=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        StringBuilder sb=new StringBuilder();
        sb.append(prefix);
        sb.append("_");
        sb.append(System.currentTimeMillis());
        sb.append(".");
        sb.append(suffix);
        return sb.toString();
    }

}


