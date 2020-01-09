package com.cloud.core.utils;

import lombok.experimental.UtilityClass;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ClassName LocalMac
 * @Description: TODO
 * @Author kevins
 * @Date 2019/12/16
 * @Version V1.0
 **/
@UtilityClass
public class LocalMac {
    /**
     * @return
     * @throws UnknownHostException,SocketException
     * @Author kevins
     * @Description 返回机器mac地址
     * @Date 2:51 下午 2019/12/17
     * @Param
     **/
    public static String getLocalMac() throws SocketException, UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        // TODO Auto-generated method stub
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append(":");
            }
            //字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
