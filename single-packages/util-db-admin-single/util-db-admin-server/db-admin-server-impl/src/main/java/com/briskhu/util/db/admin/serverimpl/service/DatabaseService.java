package com.briskhu.util.db.admin.serverimpl.service;

import com.briskhu.util.db.admin.serverimpl.mapper.DatabaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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
     * @return
     */
    public ResponseEntity<String> showDatabases(){
        LOGGER.info("[showDatabases] start...");
        ResponseEntity<String> result = new ResponseEntity<String>(databaseMapper.showDatabases().toString(), HttpStatus.OK);
        return result;
    }

    /**
     * 选择数据库
     * @param dbName
     * @return
     */
    public ResponseEntity<String> useDatabase(String dbName){
        LOGGER.info("[useDatabase] start...");
        String opsResult = null;
        ResponseEntity<String> result = null;
        try{
            opsResult = databaseMapper.useDatabase(dbName);
            result = new ResponseEntity<String>(opsResult, HttpStatus.OK);
        } catch (Exception e){
            LOGGER.error("[useDatabase] e = {}", e);
            result = new ResponseEntity<>(opsResult, HttpStatus.BAD_REQUEST);
        }

        LOGGER.debug("[useDatabase] result = {}", result);

        return result;
    }

    /**
     * 查看数据库中全部表
     * @return
     */
    public ResponseEntity<String> showTables(){
        LOGGER.info("[showTables] start...");
        ResponseEntity<String> result = new ResponseEntity<String>(databaseMapper.showTables().toString(), HttpStatus.OK);
        return result;
    }



}



