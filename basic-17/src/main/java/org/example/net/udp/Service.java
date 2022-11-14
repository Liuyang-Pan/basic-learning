package org.example.net.udp;

import java.net.*;

/**
 * purpose:DatagramSocket接收端-UDP
 *
 * @author Pan Liuyang
 * 2022/11/14 19:59
 */
public class Service {
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket(6666);
             //组播Socket
             DatagramSocket multicastSocket = new MulticastSocket(6666)
        ) {
            //加入组
            multicastSocket.joinGroup(new InetSocketAddress(InetAddress.getByName("224.0.0.1"), 6666)
                    , NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
            byte[] msg = new byte[1024 * 64];
            DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length);
            while (true) {
                datagramSocket.receive(datagramPacket);
                System.out.println("datagramPacket.getAddress() = " + datagramPacket.getAddress());
                System.out.println(new String(msg, 0, datagramPacket.getLength()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
