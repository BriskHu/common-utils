package com.briskhu.utilsingle.aesqrcommon.model;

import lombok.Data;

import java.util.LinkedHashMap;


/**
 * 程序配置Model<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-12
 **/
@Data
public class ApplicationConfigModel {

    /**
     * 密码本的访问密码
     */
    private String password;

    /**
     * 各个Aes加密相关信息的名称列表
     * 键是Aes加密关联信息的名称，值是改名称在密码本中对应记录的行数。
     */
    private LinkedHashMap<String, String> aesCorelativeList;

    public ApplicationConfigModel() {
    }

    public ApplicationConfigModel(String password) {
        this.password = password;
    }

    public ApplicationConfigModel(LinkedHashMap<String, String> aesCorelativeList) {
        this.aesCorelativeList = aesCorelativeList;
    }

    public ApplicationConfigModel(String password, LinkedHashMap<String, String> aesCorelativeList) {
        this.password = password;
        this.aesCorelativeList = aesCorelativeList;
    }

}