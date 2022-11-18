package org.example.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * purpose:DatagramSocket发送端-UDP
 *
 * @author Pan Liuyang
 * 2022/11/14 19:59
 */
public class ClientTest {
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);
            byte[] msg;
            while (true) {
                //发送消息
                System.out.println("输入您要发送的内容:");
                String s = scanner.nextLine();
                if ("exit".equals(s)) break;
                msg = s.getBytes();
                //广播方式发送255.255.255.255;组播地址:224.0.0.0 ~ 239.255.255.255
                InetAddress.getByName("255.255.255.255");
                //DatagramPacket创建数据发送
                DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 6666);
                datagramSocket.send(datagramPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
