package com.briskhu.utilsingle.aes.algorithm;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;

public class AESUtilP7 {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String CIPHER_ALGORITHM_PROVIDER = "BC";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";

    private static final Charset DATA_CHARSET = StandardCharsets.UTF_8;

    private static String offset = "8540a807b1f44920";
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private AESUtilP7() {

    }


    /**
     * aes加密
     * @param originText 待加密字符串
     * @param key 秘钥
     * @return
     */
    public static String encrypt(String originText, String key){
        try{
            return new String(Base64.encodeBase64(AESUtilP7.encrypt(originText.getBytes(DATA_CHARSET), key.getBytes(DATA_CHARSET))));
        }catch (GeneralSecurityException e){
            throw new RuntimeException("加密异常",e);
        }
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws GeneralSecurityException {
        return doFinal(key, offset.getBytes(), data, Cipher.ENCRYPT_MODE);
    }

    private static byte[] doFinal(byte[] key, byte[] offset, byte[] data, int mode) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, CIPHER_ALGORITHM_PROVIDER);
        SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(offset);
        cipher.init(mode, keySpec, iv, SecureRandom.getInstance(RANDOM_ALGORITHM));
        return cipher.doFinal(data);
    }


    /**
     * aes 解密
     * @param planText
     * @param key
     * @return
     */
    public static String decrypt(String planText, String key){
        byte[] data = Base64.decodeBase64(planText.getBytes(DATA_CHARSET));
        try {
            byte[] keyBytes = key.getBytes(DATA_CHARSET);
            byte[] result = AESUtilP7.decrypt(data, keyBytes);
            return new String(result, DATA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密异常",e);
        }
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws GeneralSecurityException {
        return doFinal(key, offset.getBytes(), data, Cipher.DECRYPT_MODE);
    }

    public static String getOffset() {
        return offset;
    }

    public static void setOffset(String offset) {
        AESUtilP7.offset = offset;
    }

    public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException {
        String originText = "{\"mac\":\"MAC000080009\",\"manufactor\":\"久氏集团\",\"model\":\"守心\",\"region\":\"510000\",\"timestamp\":\"1558430897135\"}";
        String aesP7key = "7ba271d2bf68459d";
        String encrypt = encrypt(originText, aesP7key);
        System.out.println();
        System.out.println("加密后结果: "+encrypt);
        String result = decrypt(encrypt, aesP7key);
        System.out.println("解密后结果: "+result);

    }


}
