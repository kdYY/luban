package com.cloud.core.rsa;


import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RSA
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/16
 * @Version V1.0
 **/
public abstract class RSA extends Coder {
    public static final String KEY_ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";

    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     *      * 用私钥对信息生成数字签名
     * <p>
     *      * @param data 加密数据
     * <p>
     *      * @param privateKey 私钥
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        //解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //取私钥对象
        PrivateKey pKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //用私钥生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(pKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }


    /**
     *      * 校验数字签名
     * <p>
     *      * @param data 加密数据
     * <p>
     *      * @param publicKey 公钥
     * <p>
     *      * @param sign 数字签名
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        //解密有base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //取公钥对象
        PublicKey pKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pKey);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    /**
     *      * 解密
     * <p>
     *      *  用私钥解密
     * <p>
     *      * @param data 加密数据
     * <p>
     *      * @param key
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static byte[] decryptPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        //取得私钥
        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pKey = factory.generatePrivate(encodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pKey);
        return cipher.doFinal(data);
    }

    /**
     *      * 用公钥解密
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
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        //解密
        byte[] keyBytes = decryptBASE64(key);
        //取得公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pKey = keyFactory.generatePublic(keySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pKey);
        return cipher.doFinal(data);
    }

    /**
     *      * 用公钥加密
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
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pKey = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pKey);
        return cipher.doFinal(data);
    }


    /**
     *      * 用私钥加密
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
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }


    /**
     *      * 取得私钥
     * <p>
     *      * @param keyMap
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     *      * 取得公钥
     * <p>
     *      * @param keyMap
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     *      * 初始化密钥
     * <p>
     *      * @return
     * <p>
     *      * @throws Exception
     * <p>
     *      
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PRIVATE_KEY, privateKey);
        keyMap.put(PUBLIC_KEY, publicKey);
        return keyMap;
    }
}
