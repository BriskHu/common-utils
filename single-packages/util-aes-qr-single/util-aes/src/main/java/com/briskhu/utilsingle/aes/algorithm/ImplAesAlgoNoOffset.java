package com.briskhu.utilsingle.aes.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 不带偏移量的Aes加解密行为类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-11
 **/
public class ImplAesAlgoNoOffset implements IAesAlgorithm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImplAesAlgoNoOffset.class);



    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */
    /**
     * 执行Aes加密
     *
     * @return
     */
    @Override
    public String doEncrypt() {
        return null;
    }

    /**
     * 执行Aes解密
     *
     * @return
     */
    @Override
    public String doDecrypt() {
        return null;
    }

}


