package com.briskhu.util.db.admin.serverimpl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * @return
     */
    @Select("SHOW databases")
    List<String> showDatabases();

    /**
     * 选择数据库
     * @param dbName
     * @return
     */
    @Select("USE ${dbName}")
    String useDatabase(@Param("dbName")String dbName);

    /**
     * 执行 show tables
     * @return
     */
    @Select("SHOW tables")
    List<String> showTables();
}
