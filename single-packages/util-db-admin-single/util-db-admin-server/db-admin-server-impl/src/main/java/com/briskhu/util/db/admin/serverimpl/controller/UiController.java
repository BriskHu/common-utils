package com.briskhu.util.db.admin.serverimpl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户界面入口<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-25
 **/
@Controller
@RequestMapping("/ui")
public class UiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UiController.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    @GetMapping("/mainPage")
    public String mainPage(){
        LOGGER.info("[mainPage] start...");

        return "mainPage";
    }

}


