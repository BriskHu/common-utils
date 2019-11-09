package com.briskhu.common.jgui.other;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-06
 **/
public class FileTypeUtilsTest {

    @Test
    public void getFileType() {
    }

    @Test
    public void initFileHeaderMap() {
        Map<String, String> fileMap = FileTypeUtils.initFileHeaderMap();
        for (Map.Entry<String, String> entry : fileMap.entrySet()) {
            System.out.println("FILE_TYPE_MAP.put(\"" + entry.getKey().toUpperCase() + "\", \"" + entry.getValue().toLowerCase() + "\");\t// ");
        }
    }

    @Test
    public void initFileExtMap() {
        Map<String, String> fileMap = FileTypeUtils.initFileHeaderMap();
        for (Map.Entry<String, String> entry : fileMap.entrySet()) {
            System.out.println("FILE_EXTENSION_MAP.put(\"" + entry.getValue().toLowerCase() + "\", \"" + entry.getValue().toLowerCase() + "\");\t// ");
        }
    }

    @Test
    public void getFileTypeByFilename() {
        String file = "C:\\Users\\Administrator\\Desktop\\log.txt";
        String fileType = FileTypeUtils.getFileType(file);
        System.out.println("log.txt:" + fileType);
        file = "C:\\Users\\Administrator\\Desktop\\Note.txt";
        fileType = FileTypeUtils.getFileType(file);
        System.out.println("Note.txt:" + fileType);
        file = "C:\\Users\\Administrator\\Desktop\\中国电信2018年智能家居平台建设工程系统逻辑设计说明书v1.0.docx";
        fileType = FileTypeUtils.getFileType(file);
        System.out.println("中国电信2018年智能家居平台建设工程系统逻辑设计说明书v1.0.docx:" + fileType);

        file = "C:\\Users\\Administrator\\Desktop\\T2偶数层2004版.dwg";
        fileType = FileTypeUtils.getFileType(file);
        System.out.println("T2偶数层2004版.dwg:" + fileType);
    }

    public static String getFileType(String filename) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(filename);

            byte[] bytes = new byte[10];
            if (inputStream.read(bytes, 0, bytes.length) == -1) {
                return null;
            }
            for (byte b : bytes) {
                String hv = Integer.toHexString(b);
                System.out.println(hv);
            }
            String fileCode = bytesToHexString(bytes).toLowerCase();
            System.out.println(fileCode);


            return fileCode;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (null == src || src.length < 1) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            System.out.println("v：" + v);
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    @Test
    public void testHex() {
        byte i = 31;
        String hex = Integer.toHexString(i);
        System.out.println("31=" + i + ", hex=" + hex);
        i = 'd';
        hex = Integer.toHexString(i);
        System.out.println("d=" + i + ", hex=" + hex);
        i = '\n';
        hex = Integer.toHexString(i);
        System.out.println("\\n=" + i + ", hex=" + hex);
        i = '1';
        hex = Integer.toHexString(i);
        System.out.println("1=" + i + ", hex=" + hex);
    }

    @Test
    public void temp() {
        Date markExpires = new Date();
        System.out.println("markExpires1: " + markExpires);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(markExpires);
        try {
            markExpires = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("markExpires2: " + markExpires);

        String str = null;
        System.out.println(str.length());

    }


}