package org.example.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * purpose:InterAddress使用
 *
 * @author Pan Liuyang
 * 2022/11/14 17:14
 */
public class InetAddressUsage {
    public static void main(String[] args) {
        try {
            //创建本机地址对象
            InetAddress localHost = InetAddress.getLocalHost();
            //获取主机名
            System.out.println(localHost.getHostName());
            //获取主机地址
            System.out.println(localHost.getHostAddress());

            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress.getHostName());
            System.out.println(inetAddress.getHostAddress());
            //判断是否链接 ping
            System.out.println(inetAddress.isReachable(3000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
