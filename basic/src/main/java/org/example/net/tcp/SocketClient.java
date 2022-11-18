package org.example.net.tcp;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * purpose:Socket客户端
 *
 * @author Pan Liuyang
 * 2022/11/15 10:02
 */
public class SocketClient {
    public static void main(String[] args) {
        try (
                //创建一个Socket管道请求服务端的连接
                Socket socket = new Socket("127.0.0.1", 8888)
        ) {
            //从socket管道中获取字节输出流 发送数据
            OutputStream outputStream = socket.getOutputStream();
            //将字节输出流包装成打印流
            PrintStream printStream = new PrintStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入消息:");
                String s = scanner.nextLine();
                if ("exit".equals(s)) break;
                //通过打印流发送消息
                printStream.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}