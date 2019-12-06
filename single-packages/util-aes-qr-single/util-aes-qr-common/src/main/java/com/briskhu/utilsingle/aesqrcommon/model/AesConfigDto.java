package com.briskhu.utilsingle.aesqrcommon.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-12-05
 **/
@Data
@ToString
public class AesConfigDto implements Serializable {
    private static final long serialVersionUID = 5516444684217207710L;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 配置记录主键
     */
    private String id;

    /**
     * Aes模式
     *      ALL_DEFAULT——全默认方式
     *     CBCPKCS7——CBC/PKCS7方式
     */
    private String aesMode;

    /**
     * 偏移量
     */
    private String offset;

    /**
     * AES秘钥
     */
    private String key;

    /**
     * 秘钥形式
     *     PLATIN——普通文本（无编码）
     *     BASE64——Base64编码
     */
    private String keyMode;


}