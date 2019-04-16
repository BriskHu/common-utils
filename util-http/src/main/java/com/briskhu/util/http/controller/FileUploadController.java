package com.briskhu.util.http.controller;

import com.briskhu.util.http.dto.upload.req.UploadFileAndFieldReqDto;
import com.briskhu.util.http.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-03-25
 **/
@Controller
@RequestMapping("/file")
public class FileUploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 文件上传页面
     * @return
     */
    @RequestMapping(value = "/upload")
    public String uploadExcelFile() {
        LOGGER.debug("uploadFile");

        return "htmlpages/uploadExcelFilePage";
    }

    /**
     * 基于html form的方式同时传输文件和字段
     * @param reqDto
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadFileAndField")
    public String uploadFileAndField(@Valid UploadFileAndFieldReqDto reqDto){
        LOGGER.info("[uploadFileAndField] reqDto = {}", reqDto);

        return fileUploadService.saveUploadedFile(reqDto.getUserAccount() + "-upload-", reqDto.getExcelFile());
    }

    /**
     * 基于layui的方式获取上传的文件
     * @param file
     * @param request
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getExcelFileFromHtml")
    public String getExcelFileFromHtml(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        LOGGER.info("[getExcelFileFromHtml] save file");
        String saveResult = fileUploadService.saveFile(file);

        return "success";
    }
}
