package com.cloud.core.utils;

import com.cloud.core.rsa.RSA;
import lombok.experimental.UtilityClass;

/**
 * @ClassName RSAUtil
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/16
 * @Version V1.0
 **/
@UtilityClass
public class RSAUtil {
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCY+x69XJhk04XqSN0tMc5K0rMuIiJkZ0aG5nap\n" +
            "F3+hC+yBHhqXr2YhFmb7RHARgcDeMHVkUNT8GQ9GS58qvHLjGcu+fOOmwz5/VakON6u2uTNvvsEc\n" +
            "wWrD989jtv0ExFeNboT2k+8iQRZ0B+nPHZDZpxZ3PnZ2CjqAtRFqJwoFTQIDAQAB";
    private static final String userSign = "ELC77jUAN4TgrLUrMdKQGrlUgPkk3RIR2ZuNqU8V+xq0ROfXgwd1yl7dFyNq66OvvtvsVoGctCc8\n" +
            "4AOR0pNYHphVYNkHCkSHs+18ITaIjrFNRgWQuWy7rwxO41WoZ8LLacHYO98lPEiGNEls/B/LVZEY\n" +
            "IddWRAeCmbgVeZJpt6o=";
    private static final String equipmentSign = "Lbun3aZh6SdLlYUFiLPwwRpG1JNWF5nJSde30Fp3awIZIDRqRaajeviNncttU4Ajzr8zvIhgTNsq\n" +
            "nQqq4z/OEAT19iH6ko/iXh0Zv4teLOjcoRGBvWZDRkfYiJxQdPjzZcfFpG0NMfD+32lDXfgSfSko\n" +
            "+59O+SMqnfUDAzA7Dfs=";
    //1000
    byte[] user = {76, 79, -110, 3, -101, -110, -20, 69, 17, 5, 4, -112, -127, -15, -120, -40, -40, -44, -99, 120, -97, 24, 28, 40, -26, 87, 43, -92, -42, -78, -48, -44, -20, 66, -44, -43, -121, 107, 6, -16, 21, 56, 109, 52, -37, 92, 84, 47, -46, -118, 17, -10, -127, 48, 117, 2, -106, 28, -85, 102, -73, 54, 80, -20, 16, -85, 36, -39, -107, 50, 91, -96, -73, -22, -19, 9, -70, 33, -18, -46, 77, -103, 45, -27, -61, 54, -59, -53, -12, -43, -12, 68, -66, -73, -47, -42, 64, -106, 112, -123, 105, 95, -7, 34, 40, -33, -43, -87, -66, 13, 32, -64, 66, 87, 94, -30, -73, 49, -112, 68, 85, -7, 115, -61, 45, 109, -34, 56};
    //100
    byte[] equipment = {68, 98, 116, 95, -55, 90, 35, -125, -103, 14, -29, 107, -110, 76, -62, -123, 9, -14, 30, -113, 16, -67, -125, -92, 83, 6, 105, 117, 23, 67, -14, -84, -51, -15, -73, 71, -1, -14, 71, -59, 5, 64, 70, 81, -12, -125, -95, 47, 120, -112, -80, -3, -18, 92, 55, 77, -8, -60, -121, -127, -31, 82, -95, -80, 16, 37, 26, -29, 59, 62, -55, 22, 100, 2, -127, 78, -119, 55, -91, 86, 88, 113, -69, 47, -62, 89, -18, 25, -22, 80, 56, 99, -49, -120, -38, 41, 52, 123, -70, -90, -32, -53, -5, -6, 67, 92, -122, 99, -93, -81, 115, 0, -27, 30, -106, -63, 113, 7, 111, -28, -1, -64, 98, 48, -25, 56, 74, -52};

    /*
    人员容量上限
     */
    public Integer userDecode() throws Exception {
        byte[] decodedData = RSA.decryptByPublicKey(user, publicKey);
        String outputStr = new String(decodedData);
        return Integer.parseInt(outputStr);
    }

    /*
    设备容量上限
     */
    public Integer equipmentDecode() throws Exception {
        byte[] decodedData = RSA.decryptByPublicKey(equipment, publicKey);
        String outputStr = new String(decodedData);
        return Integer.parseInt(outputStr);
    }

    /*
    用户签名验证
     */
    public boolean userVerifySign() throws Exception {
        return RSA.verify(user, publicKey, userSign);
    }

    /*
    设备签名验证
     */
    public boolean equipmentVerifySign() throws Exception {
        return RSA.verify(equipment, publicKey, equipmentSign);
    }
}
