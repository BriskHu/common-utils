package com.briskhu.utilsingle.aes.algorithm;

/**
 * Aes加密、解密行为接口<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-11
 **/
public interface IAesAlgorithm {

    /**
     * 执行Aes加密
     *
     * @return
     */
    String doEncrypt();


    /**
     * 执行Aes解密
     *
     * @return
     */
    String doDecrypt();

}
