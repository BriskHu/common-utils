package com.briskhu.util.http.controller;

import com.briskhu.util.http.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-03-25
 **/
public class FileUploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    FileUploadService fileUploadService = null;

    @RequestMapping(value = "deviceInfo/testUpload")
    public String uploadExcelFile() {
        LOGGER.debug("uploadFile");

        return "/htmlpages/uploadExcelFilePage";
    }

    @ResponseBody
    @RequestMapping(value = "deviceInfo/getExcelFileFromHtml")
    public String getExcelFileFromHtml(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        LOGGER.info("save file");
        fileUploadService = new FileUploadService();
        String saveResult = fileUploadService.saveFile(file);
        LOGGER.info(saveResult);

        return saveResult;
    }
}
