package com.briskhu.utilsingle.aes.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aes加/解密工具类<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-10
 **/
public class AesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesUtil.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private static int AES_BLOCK_SIZE = 16;
    private static int[] AES_KEYSIZES = new int[]{16, 24, 32};

    /* ---------------------------------------- methods ---------------------------------------- */
    public static final boolean isKeySizeValid(int var0) {
        for(int var1 = 0; var1 < AES_KEYSIZES.length; ++var1) {
            if (var0 == AES_KEYSIZES[var1]) {
                return true;
            }
        }

        return false;
    }

}


