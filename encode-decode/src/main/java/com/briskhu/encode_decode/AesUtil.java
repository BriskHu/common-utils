package com.briskhu.encode_decode;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * Aes加解密<p/>
 *
 * @author Brisk Hu
 * created on 2019-07-10
 **/
public class AesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesUtil.class);

    /* ---------------------------------------- fileds ---------------------------------------- */
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String CIPHER_ALGORITHM_PROVIDER = "BC";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
    private static final Charset DATA_CHARSET = StandardCharsets.UTF_8;

    /**
     * aes加解密默认设置
     */
    public static final String AES_P7KEY = "7ba271d2bf68459d";
    private static final String offset = "8540a807b1f44920";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private AesUtil() {
    }


    /* ---------------------------------------- methods ---------------------------------------- */
    public static byte[] encrypt(byte[] key, byte[] data) throws GeneralSecurityException {
        return doFinal(key, offset.getBytes(), data, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decrypt(byte[] key, byte[] data) throws GeneralSecurityException {
        return doFinal(key, offset.getBytes(), data, Cipher.DECRYPT_MODE);
    }

    private static byte[] doFinal(byte[] key, byte[] offset, byte[] data, int mode) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, CIPHER_ALGORITHM_PROVIDER);
        SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(offset);
        cipher.init(mode, keySpec, iv, SecureRandom.getInstance(RANDOM_ALGORITHM));
        return cipher.doFinal(data);
    }


    // -------------------- 加密 --------------------
    /**
     * 加密字符串</br>
     * 使用默认加密秘钥、默认偏移量。
     * @param origin 待加密字符串
     * @return
     */
    public static String encrypt(String origin){
        try{
            return new String(Base64.encodeBase64(AesUtil1.encrypt(AES_P7KEY.getBytes(DATA_CHARSET), origin.getBytes())));
        }catch (GeneralSecurityException e){
            throw new RuntimeException("加密异常",e);
        }
    }

    /**
     * 加密字符串</br>
     * 使用默认偏移量
     * @param origin 待加密字符串
     * @param key 加密秘钥
     * @return
     */
    public static String encrypt(String origin, String key){
        try{
            return new String(Base64.encodeBase64(AesUtil.encrypt(key.getBytes(DATA_CHARSET), origin.getBytes())));
        }catch (GeneralSecurityException e){
            throw new RuntimeException("加密异常",e);
        }
    }

    /**
     * 加密字符串
     * @param origin 待加密字符串
     * @param key 加密秘钥
     * @param offset 偏移量
     * @return
     */
    public static String encrypt(String origin, String key, String offset){
        try{
            return new String(Base64.encodeBase64(AesUtil.doFinal(key.getBytes(DATA_CHARSET), offset.getBytes(), origin.getBytes(), Cipher.ENCRYPT_MODE)));
        }catch (GeneralSecurityException e){
            throw new RuntimeException("加密异常",e);
        }
    }


    // -------------------- 解密 --------------------
    /**
     * 解密</br>
     * 使用默认加密秘钥、默认偏移量。
     * @param encryptedString 待解密字符串
     * @return
     */
    public static String decrypt(String encryptedString){
        byte[] data = Base64.decodeBase64(encryptedString);
        try {
            byte[] result = AesUtil.decrypt(AES_P7KEY.getBytes(DATA_CHARSET), data);
            return new String(result, DATA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密异常",e);
        }
    }

    /**
     * 解密</br>
     * 使用默认偏移量。
     * @param encryptedString 待解密字符串
     * @param key 加密秘钥
     * @return
     */
    public static String decrypt(String encryptedString, String key){
        byte[] data = Base64.decodeBase64(encryptedString);
        try {
            byte[] result = AesUtil.decrypt(AES_P7KEY.getBytes(DATA_CHARSET), data);
            return new String(result, DATA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密异常",e);
        }
    }

    /**
     * 解密</br>
     * 使用默认偏移量。
     * @param encryptedString 待解密字符串
     * @param key 加密秘钥
     * @param offset 偏移量
     * @return
     */
    public static String decrypt(String encryptedString, String key, String offset){
        byte[] data = Base64.decodeBase64(encryptedString);
        try {
            byte[] result = AesUtil.doFinal(AES_P7KEY.getBytes(DATA_CHARSET), offset.getBytes(DATA_CHARSET), data, Cipher.DECRYPT_MODE);
            return new String(result, DATA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密异常",e);
        }
    }


}


