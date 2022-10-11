package org.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * purpose:线程的运行状态
 *
 * @author Pan Liuyang
 * 2022/10/11 15:05
 */
@Slf4j
public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread threadNew = new Thread(() -> log.info("new..."), "Thread-NEW");

        Thread threadRunnable = new Thread(() -> {
            while (true) {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }, "Thread-RUNNABLE");
        threadRunnable.start();

        Thread threadTerminated = new Thread(() -> {
            log.info("TERMINATED...");
        }, "Thread-TERMINATED");
        threadTerminated.start();

        //有时限的等待
        Thread threadTimedWaiting = new Thread(() -> {
            synchronized (ThreadState.class) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-TIMED_WAITING");
        threadTimedWaiting.start();

        //无具体时间的等待
        Thread threadWaiting = new Thread(() -> {
            try {
                threadRunnable.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-WAITING");
        threadWaiting.start();

        //有时限的等待
        Thread threadBlocked = new Thread(() -> {
            //threadTimedWaiting和当前线程都加了同步锁，所以此线程的状态会进入阻塞锁的状态
            synchronized (ThreadState.class) {
                try {
                    log.info("TIMED_WAITING...");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-TIMED_WAITING");
        threadBlocked.start();

        TimeUnit.SECONDS.sleep(1);
        log.info("threadNew.getState({})", threadNew.getState());
        log.info("threadRunnable.getState({})", threadRunnable.getState());
        log.info("threadTimedWaiting.getState({})", threadTimedWaiting.getState());
        log.info("threadWaiting.getState({})", threadWaiting.getState());
        log.info("threadTerminated.getState({})", threadTerminated.getState());
        log.info("threadBlocked.getState({})", threadBlocked.getState());
    }
}
