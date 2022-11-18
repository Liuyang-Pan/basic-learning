package org.example.net.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * purpose:线程池方式
 *
 * @author Pan Liuyang
 * 2022/11/15 15:58
 */
public class SocketServerPool {
    private static final ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        try (
                //注册Socket服务端端口
                ServerSocket socketServer = new ServerSocket(8888);
        ) {
            while (true) {
                //通过多线程方式可建立多个连接
                Socket accept = socketServer.accept();
                System.out.println(accept.getRemoteSocketAddress() + "已连接");
                threadPool.execute(new RunnableTest(accept));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
