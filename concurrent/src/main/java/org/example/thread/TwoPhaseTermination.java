package org.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * purpose:两阶段终止模式实现示例
 * 系统监控终止的实现
 *
 * @author Pan Liuyang
 * 2022/10/11 11:12
 */
@Slf4j
public class TwoPhaseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                //正常运行状态被打断
                if (currentThread.isInterrupted()) {
                    //通知停止之后允许的事情
                    log.info("things to run after notification stops");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("monitoring records...");
                } catch (InterruptedException e) {
                    //在sleep、wait、join等情况被打断
                    //重新设置打断标记为true
                    currentThread.interrupt();
                    e.printStackTrace();
                }
            }
        }, "Thread-Monitor");
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
