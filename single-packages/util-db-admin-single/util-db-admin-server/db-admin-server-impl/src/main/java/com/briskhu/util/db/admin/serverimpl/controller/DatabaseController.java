package com.briskhu.util.db.admin.serverimpl.controller;

import com.briskhu.util.db.admin.serverimpl.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库的管理操作<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-19
 **/
@Controller
@RequestMapping("/database")
public class DatabaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseController.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    @Autowired
    private DatabaseService databaseService;


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 获取所有的数据库名称
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllDatabases")
    public ResponseEntity<String> getAllDatabases() {
        LOGGER.info("[getAllDatabases] 无入参。");

        return databaseService.getAllDatabases();
    }


    /**
     * 获取表的所有字段
     *
     * @param tableName
     * @return
     */
    @GetMapping("/getTableColumns")
    public ResponseEntity<String> getTableColumns(@RequestParam(value = "tableName", required = true) String tableName) {
        LOGGER.info("[getTableColumns] 入参：tableName = {}", tableName);

        return databaseService.getTableColumns(tableName);
    }

    /**
     * 查看全部数据库
     * @return
     */
    @GetMapping("/showDatabases")
    public ResponseEntity<String> showDatabases(){
        LOGGER.info("[showDatabases] start...");
        return databaseService.showDatabases();
    }

    /**
     * 选择数据库
     * @param dbName
     * @return
     */
    @GetMapping("/useDatabase/{dbName}")
    public ResponseEntity<String> useDatabase(@PathVariable("dbName") String dbName){
        LOGGER.info("[useDatabase] 入参：dbName = {}", dbName);
        return databaseService.useDatabase(dbName);
    }

    /**
     * 查看数据库中全部表
     * @return
     */
    @ResponseBody
    @GetMapping("/showTables")
    public ResponseEntity<String> showTables(){
        LOGGER.info("[showTables] start...");
        return databaseService.showTables();
    }

    @GetMapping("/layuiTestPage")
    public String layuiTestPage(){
        LOGGER.info("[layuiTestPage] start...");

        return "layuiTest";
    }


}


