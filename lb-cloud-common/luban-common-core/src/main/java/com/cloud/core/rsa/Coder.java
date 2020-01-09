package com.cloud.core.rsa;

import cn.hutool.core.codec.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * @ClassName Coder
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/16
 * @Version V1.0
 **/
public abstract class Coder {
    public static final String KEY_SHA = "SHA";

    public static final String KEY_MD5 = "MD5";


    /**
     *      * MAC算法可选以下多种算法
     * <p>
     * <p>
     *      * <pre>
     *      * HmacMD5 
     *      * HmacSHA1 
     *      * HmacSHA256 
     *      * HmacSHA384 
     *      * HmacSHA512
     *      * </pre>
     * <p>
     *      
     */
    public static final String KEY_MAC = "HmacMD5";


    /**
     *      * BASE64解密
     * <p>
     *      * @param key
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static byte[] decryptBASE64(String key) throws Exception {
//        return (new BASE64Decoder()).decodeBuffer(key);
        return Base64.decode(key);
    }

    /**
     *      * BASE64加密
     * <p>
     *      * @param key
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static String encryptBASE64(byte[] key) throws Exception {
//        return (new BASE64Encoder()).encodeBuffer(key);
        return Base64.encode(key);
    }


    /**
     *      * MD5 加密
     * <p>
     *      * @param data
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     *      * SHA 加密
     * <p>
     *      * @param data
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
    }

    /**
     *      * 初始化HMAC密钥
     * <p>
     *      * 
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     *      * HMAC  加密
     * <p>
     *      * @param data
     * <p>
     *      * @param key
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }
}
