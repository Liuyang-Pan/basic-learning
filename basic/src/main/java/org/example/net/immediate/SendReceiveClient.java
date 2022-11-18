package org.example.net.immediate;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * purpose:即时通信收发客户端
 *
 * @author Pan Liuyang
 * 2022/11/15 16:14
 */
public class SendReceiveClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            new Thread(new RunnableReceive(socket)).start();
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
                printStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 接收消息线程
 */
class RunnableReceive implements Runnable {
    private final Socket socket;

    public RunnableReceive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //从socket管道中获取到字节输入流
            InputStream inputStream = socket.getInputStream();
            //将字节输入流包装为高级的BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //逐行读取消息
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                //地址:消息
                System.out.println(Thread.currentThread().getName() + "的线程接收到来自" + socket.getRemoteSocketAddress() + "的消息:" + msg);
            }
        } catch (Exception e) {
            //服务端关闭连接
            System.out.println("服务端提出群聊...");
            e.printStackTrace();
        }
    }
}
