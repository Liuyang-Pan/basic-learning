package org.example.net.bs;

import org.example.net.immediate.PortForwardServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * purpose:简单的BS服务器模拟
 *
 * @author Pan Liuyang
 * 2022/11/15 17:30
 */
public class BSServer {
    public static void main(String[] args) {
        try {
            //注册Socket服务端端口
            ServerSocket socketServer = new ServerSocket(8888);
            while (true) {
                Socket accept = socketServer.accept();
                System.out.println("接收到" + accept.getRemoteSocketAddress() + "的请求...");
                new ResponseThread(accept).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ResponseThread extends Thread {
    private final Socket socket;

    public ResponseThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //从socket管道中获取到字节输入流
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println("HTTP/1.1 200 OK");
            printStream.println("Content-Type:text/html;charset=UTF-8");
            //发送一个空行浏览器开始接收数据
            printStream.println();
            printStream.println("<HTML><BODY><P>返回一个段落</P></BODY</HTML>");

            InputStream inputStream = socket.getInputStream();
            //将字节输入流包装为高级的BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //逐行读取消息
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                //地址:消息
                System.out.println(Thread.currentThread().getName() + "的线程接收到来自" + socket.getRemoteSocketAddress() + "的消息:" + msg);
            }
            printStream.close();
        } catch (Exception e) {
            //服务端关闭连接 移除当前socket连接
            PortForwardServer.socketList.remove(socket);
            e.printStackTrace();
        }
    }
}
