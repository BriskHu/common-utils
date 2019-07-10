package com.briskhu.encode_decode;

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

public class AesUtil1 {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String CIPHER_ALGORITHM_PROVIDER = "BC";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";

    private static final Charset DATA_CHARSET = StandardCharsets.UTF_8;

    /**
     * aes加解密
     */
    public static String AES_P7KEY = "7ba271d2bf68459d";
    private static String offset = "8540a807b1f44920";


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private AesUtil1() {

    }

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



    /**
     * 加密字符串
     * @param planText
     * @return
     */
    public static String encrypt(String planText){
        try{
            return new String(Base64.encodeBase64(AesUtil1.encrypt(AES_P7KEY.getBytes(), planText.getBytes())));
        }catch (GeneralSecurityException e){
            throw new RuntimeException("加密异常",e);
        }
    }



    /**
     * 解密
     * @param planText
     * @return
     */
    public static String decrypt(String planText){
        byte[] data = Base64.decodeBase64(planText);
        try {
            byte[] key = AES_P7KEY.getBytes(DATA_CHARSET);
            byte[] result = AesUtil1.decrypt(key, data);
            return new String(result, DATA_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密异常",e);
        }
    }



    public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException {
        String encrypt = encrypt("This is test string");
        System.out.println("加密后结果: "+encrypt);
        String result = decrypt(encrypt);
        System.out.println("解密后结果: "+result);

    }


}
