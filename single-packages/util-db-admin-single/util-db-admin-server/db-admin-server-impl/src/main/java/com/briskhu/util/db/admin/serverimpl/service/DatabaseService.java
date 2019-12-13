package com.briskhu.util.db.admin.serverimpl.service;

import com.alibaba.fastjson.JSON;
import com.briskhu.util.db.admin.serverimpl.dto.CreateTableSentenceDto;
import com.briskhu.util.db.admin.serverimpl.mapper.DatabaseMapper;
import com.briskhu.util.web.result.BasicResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-19
 **/
@Service
public class DatabaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseService.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    @Autowired
    private DatabaseMapper databaseMapper;


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * 获取所有的数据库名称
     *
     * @return
     */
    public ResponseEntity<String> getAllDatabases() {
        LOGGER.info("[getAllDatabases] start...");
        ResponseEntity<String> result = new ResponseEntity<String>(databaseMapper.listTable().toString(), HttpStatus.OK);
        return result;
    }


    /**
     * 获取表的所有字段
     *
     * @return
     */
    public ResponseEntity<String> getTableColumns(String tableName) {
        LOGGER.info("[getTableColumns] start...");
        ResponseEntity<String> result = new ResponseEntity<String>(databaseMapper.listTableColumn(tableName).toString(), HttpStatus.OK);
        return result;
    }

    /**
     * 查看全部数据库
     *
     * @return
     */
    public ResponseEntity<String> showDatabases() {
        LOGGER.info("[showDatabases] start...");
        List<String> dbList = databaseMapper.showDatabases();
        String dbListStr = JSON.toJSONString(dbList);
        ResponseEntity<String> result = new ResponseEntity<String>(dbListStr, HttpStatus.OK);
        return result;
    }

    /**
     * 获取当前操作所在的数据库
     *
     * @return
     */
    public BasicResult getCurrentDb() {
        LOGGER.info("[getCurrentDb] start...");
        String currentDb = databaseMapper.getCurrentDb();
        return BasicResult.ok().addData(currentDb);
    }

    /**
     * 选择数据库
     *
     * @param dbName
     * @return
     */
    public ResponseEntity<String> useDatabase(String dbName) {
        LOGGER.info("[useDatabase] start...");
        String opsResult = null;
        ResponseEntity<String> result = null;
        try {
            opsResult = databaseMapper.useDatabase(dbName);
            result = new ResponseEntity<String>(opsResult, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("[useDatabase] e = {}", e);
            result = new ResponseEntity<>(opsResult, HttpStatus.BAD_REQUEST);
        }

        LOGGER.debug("[useDatabase] result = {}", result);

        return result;
    }

    /**
     * 查看数据库中全部表
     *
     * @return
     */
    public ResponseEntity<String> showTables() {
        LOGGER.info("[showTables] start...");
        List<String> tableList = databaseMapper.showTables();
        String tableListStr = JSON.toJSONString(tableList);
        ResponseEntity<String> result = new ResponseEntity<String>(tableListStr, HttpStatus.OK);
        return result;
    }

    /**
     * 获取指定数据库中指定表的所有字段
     *
     * @param dbName
     * @param tableName
     * @return
     */
    public ResponseEntity<String> showFields(String dbName, String tableName) {
        LOGGER.info("[showFields] start...");
        List<String> fieldsList = databaseMapper.showFields(dbName, tableName);
        String fieldsListStr = JSON.toJSONString(fieldsList);
        ResponseEntity<String> result = new ResponseEntity<>(fieldsListStr, HttpStatus.OK);
        return result;
    }

    public BasicResult showFieldsByResult(String dbName, String tableName) {
        LOGGER.info("[showFields] start...");
        List<String> fieldsList = databaseMapper.showFields(dbName, tableName);
        return BasicResult.ok().addData(fieldsList);
    }

    /**
     * 展示指定数据库指定表的建表语句
     *
     * @param tableName
     * @return
     */
    public BasicResult showCreateTableForOne(String dbName, String tableName) {
        LOGGER.info("[showCreateTableForOne] start...");

        if (dbName == null) {
            databaseMapper.useDatabase(databaseMapper.getCurrentDb());
        } else {
            tableName = dbName + "." + tableName;
        }

        CreateTableSentenceDto createTableSentenceDto = null;
        try {
            createTableSentenceDto = databaseMapper.showCreateTableForOne(tableName);
        } catch (BadSqlGrammarException be) {
            LOGGER.error("[showCreateTableForOne] BadSqlGrammarException: errMsg = {}.", be.getCause().getMessage());
            return BasicResult.fail(be.getCause().getMessage());
        }

        return BasicResult.ok().addData(createTableSentenceDto);
    }

    /**
     * 展示指定数据库中全部表的建表语句
     * @param dbName
     * @return
     */
    public BasicResult showCreateTableForAll(String dbName){
        LOGGER.info("[showCreateTableForAll] start...");
        BasicResult result = null;

        try{
            databaseMapper.useDatabase(dbName);
            List<String> tableList = databaseMapper.showTables();
            LOGGER.debug("[showCreateTableForAll] tableList.size = {}.", tableList.size());
            if (tableList.size()>0){
                List<CreateTableSentenceDto> createTableSentenceList = new ArrayList<>();
                for (String tableName : tableList){
                    createTableSentenceList.add(databaseMapper.showCreateTableForOne(tableName));
                }

                LOGGER.info("[showCreateTableForAll] successful end.");
                result = BasicResult.ok().addData(createTableSentenceList);
            }else {
                LOGGER.info("[showCreateTableForAll] successful end.");
                result = BasicResult.ok("该数据库中不存在数据表。");
            }
        } catch (BadSqlGrammarException bsge){
            LOGGER.error("[showCreateTableForOne] BadSqlGrammarException: errMsg = {}.", bsge.getCause().getMessage());
            result = BasicResult.fail(bsge.getCause().getMessage());
        }

        return result;
    }

}



