package org.example.net.immediate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * purpose:端口转发服务端
 *
 * @author Pan Liuyang
 * 2022/11/15 16:21
 */
public class PortForwardServer {
    public static List<Socket> socketList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            //注册Socket服务端端口
            ServerSocket socketServer = new ServerSocket(8888);
            while (true) {
                Socket accept = socketServer.accept();
                System.out.println(accept.getRemoteSocketAddress() + "客户端已上线...");
                socketList.add(accept);
                new Thread(new RunnablePortForward(accept)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class RunnablePortForward implements Runnable {
    private final Socket socket;

    public RunnablePortForward(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //从socket管道中获取到字节输入流
        try {
            InputStream inputStream = socket.getInputStream();
            //将字节输入流包装为高级的BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //逐行读取消息
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                //地址:消息
                System.out.println(Thread.currentThread().getName() + "的线程接收到来自" + socket.getRemoteSocketAddress() + "的消息:" + msg);
                sendMsgToAll(socket, msg);
            }
        } catch (Exception e) {
            //服务端关闭连接 移除当前socket连接
            PortForwardServer.socketList.remove(socket);
            e.printStackTrace();
        }
    }

    /**
     * 发送消息给在线的所有的客户端
     *
     * @param msg 发送的消息
     */
    private void sendMsgToAll(Socket socket, String msg) {
        for (Socket allSocket : PortForwardServer.socketList) {
            try {
                if (allSocket.getRemoteSocketAddress().equals(socket.getRemoteSocketAddress())) continue;
                PrintStream printStream = new PrintStream(allSocket.getOutputStream());
                printStream.println(msg);
                printStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
