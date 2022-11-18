package org.example.net.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * purpose:ServerSocket服务端
 *
 * @author Pan Liuyang
 * 2022/11/15 10:02
 */
public class SocketServer {
    public static void main(String[] args) {
        try (
                //注册Socket服务端端口
                ServerSocket socketServer = new ServerSocket(8888);
        ) {
            //调用accept()方法等待客户端的连接请求 这种方式仅能建立单个连接,通过多线程方式可建立多个连接
            Socket accept = socketServer.accept();
            //从socket管道中获取到字节输入流
            InputStream inputStream = accept.getInputStream();
            //将字节输入流包装为高级的BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //逐行读取消息
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                //地址:消息
                System.out.println(accept.getRemoteSocketAddress() + ":" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MultipleSocketServer {
    public static void main(String[] args) {

        try (
                //注册Socket服务端端口
                ServerSocket socketServer = new ServerSocket(8888);
                ExecutorService threadPool = Executors.newCachedThreadPool();
        ) {
            while (true) {
                //通过多线程方式可建立多个连接
                Socket accept = socketServer.accept();
                threadPool.execute(new RunnableTest(accept));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class RunnableTest implements Runnable {
    private final Socket socket;

    public RunnableTest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //从socket管道中获取到字节输入流
        try (InputStream inputStream = socket.getInputStream()) {
            //将字节输入流包装为高级的BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //逐行读取消息
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                //地址:消息
                System.out.println(Thread.currentThread().getName() + "的线程接收到来自" + socket.getRemoteSocketAddress() + "的消息:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
