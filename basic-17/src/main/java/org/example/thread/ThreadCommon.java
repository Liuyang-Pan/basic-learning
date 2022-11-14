package org.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * purpose:线程的常用方法
 *
 * @author Pan Liuyang
 * 2022/11/13 22:37
 */
@Slf4j
public class ThreadCommon {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            //设置线程的名称
            currentThread.setName("Thread-current");
            //getName()获取线程的名称
            log.info(currentThread.getName() + " is Running...");
            log.info("start sleep...");
            try {
                //通过TimeUnit工具类休眠5s
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            log.info("end sleep...");
        });
        //设置线程优先级，范围:1~10,1最低,10最高,5是默认值,只能反映线程执行优先程度不能决定系统一定按这个顺序执行。
        thread.setPriority(1);
        log.info("current thread priority is " + thread.getPriority());
        //启动线程
        thread.start();
        log.info(Thread.currentThread().getName() + " is Running...");
        //等待某个线程执行完毕
        thread.join();
        log.info("thread execution completed...");

    }
}
