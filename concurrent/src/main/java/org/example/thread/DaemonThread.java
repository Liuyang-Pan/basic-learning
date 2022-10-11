package org.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * purpose:守护线程
 *
 * @author Pan Liuyang
 * 2022/10/11 14:45
 */
@Slf4j
public class DaemonThread {

    /**
     * 守护线程的用法：
     * 守护线程是一种特殊的线程,当进程中不存在非守护线程了,则守护线程自动销毁。即使守护线程中的代码未执行完成。
     * 垃圾回收器线程就是一种守护线程
     * Tomcat中的Acceptor和Poller线程都是守护线程，所以Tomcat 接收到shutdown命令后，不会等待它们处理完当前请求
     */
    @Test
    public void daemonThreadUsage() throws InterruptedException {
        Thread threadDaemon = new Thread(() -> {
            do {
                log.info("daemon thread running...");
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!Thread.currentThread().isInterrupted());
        }, "Thread-Daemon");
        threadDaemon.setDaemon(true);
        threadDaemon.start();
        TimeUnit.SECONDS.sleep(5);
    }
}
