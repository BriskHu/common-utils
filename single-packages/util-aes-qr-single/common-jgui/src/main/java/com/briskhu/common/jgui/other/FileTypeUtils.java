package com.briskhu.common.jgui.other;

import com.briskhu.common.jgui.algorithm.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-04
 **/
public class FileTypeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileTypeUtils.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private final static Map<String, String> FILE_HEADER_MAP = new LinkedHashMap<>();
    private final static Map<String, String> FILE_EXTENSION_MAP = new HashMap<>();
    private static String unknownType = "未知的文件格式";
    private static String nullFile = "文件内容为空";
    private static String notFile = "不是一个文件";
    private static String fileNotExist = "文件不存在";


    /* ---------------------------------------- methods ---------------------------------------- */
    private FileTypeUtils() {
    }

    static {
        initFileHeaderMap(); //初始化文件类型信息
        initFileExtMap();
    }

    /**
     * 常见文件头信息
     */
    public static Map initFileHeaderMap() {
        //image
        FILE_HEADER_MAP.put("FFD8FFE000104A464946", "jpg");    // JPEG (jpg)
        FILE_HEADER_MAP.put("89504E470D0A1A0A0000", "png");    //
        FILE_HEADER_MAP.put("47494638396126026F01", "gif");    //
        FILE_HEADER_MAP.put("49492A00227105008037", "tif");    // TIFF (tif)
        FILE_HEADER_MAP.put("424D228C010000000000", "bmp");    // 16色位图(bmp)
        FILE_HEADER_MAP.put("424D8240090000000000", "bmp");    // 24位位图(bmp)
        FILE_HEADER_MAP.put("424D8E1B030000000000", "bmp");    // 256色位图(bmp)

        //doc
        FILE_HEADER_MAP.put("D0CF11E0A1B11AE10000", "doc");    //MS doc 注意：word、msi、excel(exl)、PowerPoint(ppt)、visio(vsd)及wps的文字、表格、ppt的文件头都是这个
        FILE_HEADER_MAP.put("504B0304140006000800", "docx");    //
        FILE_HEADER_MAP.put("504B03040A0000000008", "docx");    //
        FILE_HEADER_MAP.put("255044462D312E350D0A", "pdf");    //
        FILE_HEADER_MAP.put("504B0304140000000800", "zip");    //
        FILE_HEADER_MAP.put("526172211A0700CF9073", "rar");    //
        FILE_HEADER_MAP.put("6431303A637265617465", "torrent");    //
        FILE_HEADER_MAP.put("FF575043", "wpd");    //

        // plain design
        FILE_HEADER_MAP.put("41433130313500000000", "dwg");    //CAD (dwg)
        FILE_HEADER_MAP.put("41433130323400000000", "dwg");    //
        FILE_HEADER_MAP.put("41433130313800000000", "dwg");    //
        FILE_HEADER_MAP.put("41433130", "dwg");    //CAD (dwg)
        FILE_HEADER_MAP.put("38425053000100000000", "psd");    //Photoshop (psd)
        FILE_HEADER_MAP.put("252150532D41646F6265", "ps");    //

        // program
        FILE_HEADER_MAP.put("5374616E64617264204A", "mdb");    //MS Access (mdb)
        FILE_HEADER_MAP.put("235468697320636F6E66", "ini");    //
        FILE_HEADER_MAP.put("504B03040A0000000000", "jar");    //
        FILE_HEADER_MAP.put("4D5A9000030000000400", "exe");    //可执行文件
        FILE_HEADER_MAP.put("4D5A9000", "dll");    //动态链接库文件
        FILE_HEADER_MAP.put("7061636B616765207765", "java");    //
        FILE_HEADER_MAP.put("4D616E69666573742D56", "mf");    //MF文件
        FILE_HEADER_MAP.put("406563686F206F66660D", "bat");    //
        FILE_HEADER_MAP.put("6C6F67346A2E726F6F74", "properties");    //
        FILE_HEADER_MAP.put("CAFEBABE0000002E0041", "class");    //
        FILE_HEADER_MAP.put("494E5345525420494E54", "sql");    //

        // web
        FILE_HEADER_MAP.put("3C21444F435459504520", "html");    //
        FILE_HEADER_MAP.put("68746D6C3E", "html");    //
        FILE_HEADER_MAP.put("68746D6C", "html");    //
        FILE_HEADER_MAP.put("3C21646F637479706520", "htm");    //
        FILE_HEADER_MAP.put("48544D4C207B0D0A0942", "css");    //
        FILE_HEADER_MAP.put("696B2E71623D696B2E71", "js");    //
        FILE_HEADER_MAP.put("7B5C727466315C616E73", "rtf");    //Rich Text Format (rtf)
        FILE_HEADER_MAP.put("46726F6D3A203D3F6762", "eml");    //Email [Outlook Express 6] (eml)
        FILE_HEADER_MAP.put("44656C69766572792D64", "eml");    //邮件
        FILE_HEADER_MAP.put("3C25402070616765206C", "jsp");    //
        FILE_HEADER_MAP.put("3C3F786D6C2076657273", "xml");    //
        FILE_HEADER_MAP.put("CFAD12FEC5FD746F", "dbx");    //Outlook Express (dbx)
        FILE_HEADER_MAP.put("CFAD12FE", "dbx");    //Outlook Express (dbx)
        FILE_HEADER_MAP.put("2142444E", "pst");    //Outlook (pst)

        //video
        FILE_HEADER_MAP.put("2E524D46000000120001", "rmvb");    //rmvb/rm相同
        FILE_HEADER_MAP.put("464C5601050000000900", "flv");    //flv与f4v相同
        FILE_HEADER_MAP.put("00000020667479706D70", "mp4");    //
        FILE_HEADER_MAP.put("000001BA210001000180", "mpg");    //
        FILE_HEADER_MAP.put("6D6F6F76", "mov");    //
        FILE_HEADER_MAP.put("41564920", "avi");    //
        FILE_HEADER_MAP.put("000001B3", "mpg");    //

        //audio
        FILE_HEADER_MAP.put("49443303000000002176", "mp3");    //
        FILE_HEADER_MAP.put("3026B2758E66CF11A6D9", "wmv");    //wmv与asf相同
        FILE_HEADER_MAP.put("57415645", "wav");    //
        FILE_HEADER_MAP.put("3026B2758E66CF11", "asf");    //
        FILE_HEADER_MAP.put("3026B275", "asf");    //
        FILE_HEADER_MAP.put("52494646E27807005741", "wav");    //
        FILE_HEADER_MAP.put("52494646D07D60074156", "avi");    //
        FILE_HEADER_MAP.put("4D546864000000060001", "mid");    //MIDI (mid)
        FILE_HEADER_MAP.put("2E7261FD", "ram");    //Real Audio (ram)


        // other
        FILE_HEADER_MAP.put("1F8B0800000000000000", "gz");    //
        FILE_HEADER_MAP.put("49545346030000006000", "chm");    //
        FILE_HEADER_MAP.put("04000000010000001300", "mxp");    //
        FILE_HEADER_MAP.put("AC9EBD8F", "qdf");    //Quicken (qdf)
        FILE_HEADER_MAP.put("E3828596", "pwl");    //Windows Password (pwl)

        return FILE_HEADER_MAP;
    }

    /**
     * 常见文件扩展名信息
     */
    public static Map initFileExtMap() {
        //image
        FILE_EXTENSION_MAP.put("jpg", "jpg");    //
        FILE_EXTENSION_MAP.put("jpeg", "jpg");    //
        FILE_EXTENSION_MAP.put("png", "png");    //
        FILE_EXTENSION_MAP.put("gif", "gif");    //
        FILE_EXTENSION_MAP.put("tif", "tif");    //
        FILE_EXTENSION_MAP.put("bmp", "bmp");    //

        //doc
        FILE_EXTENSION_MAP.put("txt", "txt");    //
        FILE_EXTENSION_MAP.put("doc", "doc");    //
        FILE_EXTENSION_MAP.put("vsd", "vsd");    //
        FILE_EXTENSION_MAP.put("xls", "xls");    //
        FILE_EXTENSION_MAP.put("ppt", "ppt");    //

        FILE_EXTENSION_MAP.put("docx", "docx");    //
        FILE_EXTENSION_MAP.put("pdf", "pdf");    //
        FILE_EXTENSION_MAP.put("zip", "zip");    //
        FILE_EXTENSION_MAP.put("rar", "rar");    //
        FILE_EXTENSION_MAP.put("torrent", "torrent");    //
        FILE_EXTENSION_MAP.put("wpd", "wpd");    //

        // plain design
        FILE_EXTENSION_MAP.put("dwg", "dwg");    //
        FILE_EXTENSION_MAP.put("psd", "psd");    //
        FILE_EXTENSION_MAP.put("ps", "ps");    //

        // program
        FILE_EXTENSION_MAP.put("mdb", "mdb");    //
        FILE_EXTENSION_MAP.put("ini", "ini");    //
        FILE_EXTENSION_MAP.put("jar", "jar");    //
        FILE_EXTENSION_MAP.put("exe", "exe");    //
        FILE_EXTENSION_MAP.put("dll", "dll");    //
        FILE_EXTENSION_MAP.put("java", "java");    //
        FILE_EXTENSION_MAP.put("mf", "mf");    //
        FILE_EXTENSION_MAP.put("bat", "bat");    //
        FILE_EXTENSION_MAP.put("properties", "properties");    //
        FILE_EXTENSION_MAP.put("class", "class");    //
        FILE_EXTENSION_MAP.put("sql", "sql");    //

        // web
        FILE_EXTENSION_MAP.put("html", "html");    //
        FILE_EXTENSION_MAP.put("htm", "htm");    //
        FILE_EXTENSION_MAP.put("css", "css");    //
        FILE_EXTENSION_MAP.put("js", "js");    //
        FILE_EXTENSION_MAP.put("rtf", "rtf");    //
        FILE_EXTENSION_MAP.put("eml", "eml");    //
        FILE_EXTENSION_MAP.put("jsp", "jsp");    //
        FILE_EXTENSION_MAP.put("xml", "xml");    //
        FILE_EXTENSION_MAP.put("dbx", "dbx");    //
        FILE_EXTENSION_MAP.put("pst", "pst");    //

        //video
        FILE_EXTENSION_MAP.put("rmvb", "rmvb");    //
        FILE_EXTENSION_MAP.put("rm", "rm");    //
        FILE_EXTENSION_MAP.put("flv", "flv");    //
        FILE_EXTENSION_MAP.put("f4v", "f4v");    //
        FILE_EXTENSION_MAP.put("mp4", "mp4");    //
        FILE_EXTENSION_MAP.put("mpg", "mpg");    //
        FILE_EXTENSION_MAP.put("mov", "mov");    //
        FILE_EXTENSION_MAP.put("avi", "avi");    //
        FILE_EXTENSION_MAP.put("mpg", "mpg");    //

        //audio
        FILE_EXTENSION_MAP.put("mp3", "mp3");    //
        FILE_EXTENSION_MAP.put("wmv", "wmv");    //
        FILE_EXTENSION_MAP.put("wav", "wav");    //
        FILE_EXTENSION_MAP.put("asf", "asf");    //
        FILE_EXTENSION_MAP.put("wav", "wav");    //
        FILE_EXTENSION_MAP.put("avi", "avi");    //
        FILE_EXTENSION_MAP.put("mid", "mid");    //
        FILE_EXTENSION_MAP.put("ram", "ram");    //

        // other
        FILE_EXTENSION_MAP.put("gz", "gz");    //
        FILE_EXTENSION_MAP.put("chm", "chm");    //
        FILE_EXTENSION_MAP.put("mxp", "mxp");    //
        FILE_EXTENSION_MAP.put("qdf", "qdf");    //
        FILE_EXTENSION_MAP.put("pwl", "pwl");    //

        return FILE_HEADER_MAP;
    }


    /**
     * 通过扩展名获取文件类型
     * 有一些重要的文件，没有固定的文件头。该方法针对以下文件类型：
     * TXT 没固定文件头定义；按字节读取时，读到的是相应字符的ASCII码
     * TMP 没固定文件头定义
     * INI 没固定文件头定义
     * BIN 没固定文件头定义
     * DBF 没固定文件头定义
     * C 没没固定文件头定义
     * CPP 没固定文件头定义
     * H 没固定文件头定义
     * BAT 没固定文件头定义
     *
     * @param filename
     * @return
     */
    public static String getFileTypeByExt(String filename) {
        LOGGER.debug("[getFileTypeByExt] filename = {}", filename);
        if (filename.lastIndexOf(".") != -1) {
            String splitedExt = filename.substring(filename.lastIndexOf(".") + 1);
            return FILE_EXTENSION_MAP.get(splitedExt);
        }

        return null;
    }

    /**
     * @param filename
     * @return
     */
    public static String getFileType(String filename) {
        File file = new File(filename);

        return getFileType(file);
    }

    /**
     * @param file
     * @return
     * @throws RuntimeException
     */
    public static String getFileType(File file) throws RuntimeException {
        LOGGER.debug("[getFileType] file = {}", file);
        String result = null;

        if (!file.exists()) {
            LOGGER.error("[getFileType] {}", fileNotExist);
            return result;
        } else if (!file.isFile()) {
            LOGGER.error("[getFileType] {}", notFile);
            return result;
        }

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[10];
            if (inputStream.read(bytes, 0, bytes.length) == -1) {
                LOGGER.error("[getFileType] {}", nullFile);
            }

            String fileCode = bytesToHexString(bytes).toUpperCase();
            LOGGER.debug("[getFileType] fileCode = {}", fileCode);
            if (fileCode != null) {
                if (FILE_HEADER_MAP.containsKey(fileCode)) {
                    result = FILE_HEADER_MAP.get(fileCode);
                } else if (FILE_HEADER_MAP.containsKey(fileCode.substring(0, 7))) {
                    result = FILE_HEADER_MAP.get(fileCode.substring(0, 7));
                } else {
                    result = getFileTypeByExt(file.getName());
                    if (result == null) {
                        LOGGER.error("[getFileType] {}", unknownType);
                    }
                }
            } else {
                LOGGER.error("[getFileType] {}", nullFile);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("[getFileType] {}", fileNotExist);
        } catch (IOException e) {
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

        return result;
    }


    /**
     * 获取文件头
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (null == src || src.length < 1) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}