package com.briskhu.util.db.admin.serverimpl.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 建表语句<p/>
 *
 * @author Brisk Hu
 * created on 2019-12-12
 **/
@Data
public class CreateTableSentenceDto implements Serializable {
    private static final long serialVersionUID = 8897804331143991696L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 建表语句
     */
    private String tableDesc;

}