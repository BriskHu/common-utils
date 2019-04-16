package com.briskhu.util.http.service;

import com.briskhu.util.http.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-03-25
 **/
@Service
public class FileUploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    @Value("${file.storepath}")
    private String fileSavePath;

    @Autowired
    private FileUtils fileUtils;

    /* ---------------------------------------- methods ---------------------------------------- */


    /**
     * 保存上传文件<p/>
     * Not use!
     *
     * @param file
     * @return
     */
    public String saveFile(MultipartFile file) {
        String result = "";

        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            String savePath = fileSavePath + saveFileName;
            LOGGER.debug("[saveFile] savePath: " + savePath);
            File saveFile = new File(savePath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
//                result = saveFile.getName() + " 上传成功";
                result = savePath;
            } catch (FileNotFoundException ffe) {
                ffe.printStackTrace();
                return "上传失败," + ffe.getMessage();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return "上传失败," + ioe.getMessage();
            }
        } else {
            return "上传失败，因为文件为空.";
        }

        return result;
    }

    /**
     * 保存上传文件<p/>
     * @param file
     * @return
     */
    public String saveUploadedFile(MultipartFile file) {
        String result = "";

        String originalName = file.getOriginalFilename();
        String filePath = fileSavePath;
        String newName = fileUtils.renameFile(originalName);
        LOGGER.debug("读取存储路径:{}", filePath);
        try {
            fileUtils.storeFile(file.getBytes(), filePath, newName);
        } catch (IOException e) {
            LOGGER.debug("存储excel文件出错");
        }
        result = filePath + newName;

        return result;
    }


    /**
     * 保存上传文件<p/>
     * @param businessPrefix 为区分文件用途，而加的业务信息前缀.比如用来区分文件所属用户、对应接口。
     *                       前缀内部各字段之间用下划线进行分割，前缀和原始文件名之间用连字符分割。
     *                       比如用于直连设备批量导入的文件将会保存为“18900001111_deviceReg-直连设备Excel表.xls”。
     * @param file
     * @return
     */
    public String saveUploadedFile(String businessPrefix, MultipartFile file) {
        String result = "";

        String originalName = file.getOriginalFilename();
        String filePath = fileSavePath;
        String newName = fileUtils.renameFile(businessPrefix + originalName);
        LOGGER.debug("读取存储路径:{}", filePath);
        try {
            fileUtils.storeFile(file.getBytes(), filePath, newName);
        } catch (IOException e) {
            LOGGER.debug("存储excel文件出错");
        }
        result = filePath + newName;

        return result;
    }

}


