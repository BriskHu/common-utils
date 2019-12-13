package com.briskhu.util.db.admin.serverimpl.mapper;

import com.briskhu.util.db.admin.serverimpl.dto.CreateTableSentenceDto;
import org.apache.ibatis.annotations.*;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.List;
import java.util.Map;

/**
 * 表  的Mapper <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-19
 **/
@Mapper
public interface DatabaseMapper {

    @Select("SELECT * FROM information_schema.TABLES where TABLE_SCHEMA=(SELECT database())")
    List<Map> listTable();

    @Select("SELECT * FROM information_schema.COLUMNS where TABLE_SCHEMA=(SELECT database()) and TABLE_NAME=#{tableName}")
    List<Map> listTableColumn(String tableName);

    /**
     * 执行 show databases
     *
     * @return
     */
    @Select("SHOW databases")
    List<String> showDatabases();

    /**
     * 获取当前操作所在的数据库
     * @return
     */
    @Select("SELECT database()")
    String getCurrentDb();

    /**
     * 选择数据库
     *
     * @param dbName
     * @return
     */
    @Select("USE ${dbName}")
    String useDatabase(@Param("dbName") String dbName) throws BadSqlGrammarException;

    /**
     * 执行 show tables
     *
     * @return
     */
    @Select("SHOW tables")
    List<String> showTables();

    /**
     * 获取指定数据库指定表的全部字段名称
     *
     * @return
     */
    @Select("SELECT COLUMN_NAME as columnName FROM information_schema.COLUMNS where TABLE_SCHEMA=#{dbName} and TABLE_NAME=#{tableName}")
    List<String> showFields(@Param("dbName") String dbName, @Param("tableName") String tableName);

    /**
     * 获取指定数据库指定表的建表语句
     * @param tableName
     * @return
     */
    @Select("SHOW CREATE TABLE ${tableName}")
    @Results(id = "resCreateTable",
            value = {@Result(column = "Table", property = "tableName"), @Result(column = "Create Table", property = "tableDesc")})
    CreateTableSentenceDto showCreateTableForOne(String tableName) throws BadSqlGrammarException;



}
