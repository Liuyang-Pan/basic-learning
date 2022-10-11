package org.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * purpose:线程常用方法
 *
 * @author Pan Liuyang
 * 2022/10/10 10:55
 */
@Slf4j(topic = "ThreadCommonMethod")
public class ThreadCommonMethod {

    /**
     * start & run
     * start：启动一个新的线程，在新的线程中运行run方法的程序
     * run：新线程启动后调用的方法
     */
    @Test
    public void startAndRun() {
        Thread threadRun = new Thread(() -> log.info("Run..."), "Thread-Run");
        Thread threadStart = new Thread(() -> log.info("Start..."), "Thread-Start");

        //直接调用run方法相当于在主线程（main）中直接调用run方法，并没有创建线程去运行
        threadRun.run();
        //查看一下线程运行状态
        log.info(String.valueOf(threadStart.getState()));
        //调用start方法会创建线程运行
        threadStart.start();
        //查看一下线程运行状态
        log.info(String.valueOf(threadStart.getState()));
        //同一个线程无法调用两次start，否则会抛出IllegalThreadStateException异常
//        threadStart.start();
    }

    /**
     * sleep：调用sleep会让当前线程从RUNNING状态进入到TIMED_WAITING（阻塞）状态
     * 线程睡眠结束后未必立刻执行，需要等待调度器调度
     */
    @Test
    public void sleepUsage() {
        Thread threadSleep = new Thread(() -> {
            try {
                log.info("Sleep...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-Sleep");

        log.info("Thread-Sleep.the state before calling start: {}", threadSleep.getState());
        threadSleep.start();
        //主线程先执行了这一句会有可能threadSleep未运行到sleep时会打印出RUNNABLE的运行状态
        log.info("Thread-Sleep.the state after calling start: {}", threadSleep.getState());
        //让主线程睡眠500毫秒，避免主线程在threadSleep未睡眠时打印了threadSleep的状态。
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //此处可以看到睡眠的线程状态是TIMED_WAITING
        log.info("Thread-Sleep.the state after calling start: {}", threadSleep.getState());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * interrupt
     */
    @Test
    public void sleepInterruptUsage() throws InterruptedException {
        Thread threadInterrupt = new Thread(() -> {
            try {
                log.info("Sleep...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info("Wake up...");
                e.printStackTrace();
            }
        }, "Thread-Interrupt");
        threadInterrupt.start();
        Thread.sleep(1000);
        log.info("Interrupt...");
        //睡眠中的线程可以通过interrupt打断，这是sleep方法会抛出InterruptedException
        threadInterrupt.interrupt();

        //推荐使用TimeUnit中的sleep实现线程睡眠，获得更好的可读性
        Thread threadTimeUnit = new Thread(() -> {
            try {
                log.info("TimeUnit.SECONDS.sleep(2000)");
                //睡眠两秒 TimeUnit内部实际还是调用的Thread.sleep()
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-TimeUnit.sleep");
        threadTimeUnit.start();
    }

    /**
     * yield:调用yield会让当前线程从RUNNING状态进入到RUNNABLE（就绪）状态，让出当前线程的使用权(主动让出）
     */
    @Test
    public void yieldUsage() {
        Thread threadYield = new Thread(() -> {
            log.info("Yield...");
            Thread.yield();
        }, "Thread-Yield");

        Thread threadOther = new Thread(() ->
                log.info("other threads")
                , "Thread-Other");
        threadYield.start();
        log.info(String.valueOf(threadYield.getState()));
        threadOther.start();
    }

    /**
     * priority:可以通过.setPriority()设置线程优先级，仅仅是一个提示，调度器可以忽略它
     * CPU繁忙时，优先级高的线程会获得更多的时间片，CPU闲暇时，优先级几乎无作用
     */
    @Test
    public void priorityUsage() {
        Runnable runnableOne = () -> {
            int count = 0;
            for (; ; ) {
//                System.out.println("Runnable-One:" + count++);
                log.info("Runnable-One:{}", count++);
            }
        };

        Runnable runnableTwo = () -> {
            int count = 0;
            for (; ; ) {
                Thread.yield();
//                System.out.println("Runnable-Two:" + count++);
                log.info("Runnable-Two:     {}", count++);
            }
        };

        Thread threadOne = new Thread(runnableOne, "Thread-One");
        Thread threadTwo = new Thread(runnableTwo, "Thread-Two");
        //线程二的优先级为最大，执行会发现增加的数量是最多的
        threadOne.setPriority(Thread.MIN_PRIORITY);
        threadTwo.setPriority(Thread.MAX_PRIORITY);
        threadOne.start();
        threadTwo.start();
    }

    /**
     * while(true)时通过睡眠一会儿防止CPU占满
     */
    @Test
    public void sleepCase() {
        new Thread(() -> {
            while (true) {
                log.info("Running...");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    int joinInt = 0;

    /**
     * join：等待线程运行结束后再执行后面的代码，join的底层实现是wait
     * join(long n)：最大等待n毫秒，超时不会继续等待,若线程在等待时间内结束后继续执行下面的代码
     */
    @Test
    public void joinUsage() throws InterruptedException {
        Thread threadJoin = new Thread(() -> {
            try {
                log.info("start execution Thread-Join");
                TimeUnit.SECONDS.sleep(1);
                joinInt = 10;
                log.info("end execution Thread-Join");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-Join");
        log.info("start calling the start method");
        threadJoin.start();
        //等待threadJoin线程执行完成，若此处不调用join方法，异步执行会直接先打印joinInt = 0
        log.info("start calling the join method...");
//        threadJoin.join();
        threadJoin.join(50);
        log.info("end calling the join method...");
        log.info("end run, joinInt = {}", joinInt);
    }

    int joinIntOne = 1;
    int joinIntTwo = 2;

    /**
     * join等待多个线程实例
     */
    @Test
    public void multipleJoinUsage() throws InterruptedException {
        Thread threadJoinOne = new Thread(() -> {
            try {
                log.info("start execution Thread-JoinOne");
                TimeUnit.SECONDS.sleep(1);
                joinIntOne = 10;
                log.info("end execution Thread-JoinOne");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-JoinOne");

        Thread threadJoinTwo = new Thread(() -> {
            try {
                log.info("start execution Thread-JoinTwo");
                TimeUnit.SECONDS.sleep(2);
                joinIntTwo = 20;
                log.info("end execution Thread-JoinTwo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-JoinTwo");

        long startTime = System.currentTimeMillis();
        threadJoinOne.start();
        threadJoinTwo.start();
        log.info("start executing the first join method");
        threadJoinOne.join();
        log.info("start executing the second join method");
        threadJoinTwo.join();
        long endTime = System.currentTimeMillis();
        log.info("joinIntOne = {} , joinIntTwo = {} , total time:{}ms", joinIntOne, joinIntTwo, endTime - startTime);
    }

    /**
     * interrupt详解使用：interrupt可以打断正在运行的或者是阻塞状态（这三种方法也会阻塞sleep、wait、join）的线程
     * isInterrupted() 判断打断标记（不会清除打断标记-重置为false）
     * static interrupted() 判断打断标记（清除打断标记-重置为false）
     */
    @Test
    public void interruptUsage() throws InterruptedException {
        Thread threadInterruptOne = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-interruptOne");

        threadInterruptOne.start();
        TimeUnit.SECONDS.sleep(2);
        //主线程打断它，会抛出InterruptedException
        log.info("interrupt...");
        threadInterruptOne.interrupt();
        //isInterrupted():可通过打断标记判断线程是否就此终止或继续运行
        //打断标记:若是正常运行状态被打断，结果为true，若是sleep、wait、join的阻塞状态的打断结果为false（会清空状态标识）
        log.info("interrupt status:{}", threadInterruptOne.isInterrupted());

        Thread threadInterruptTwo = new Thread(() -> {
            while (true) {
                log.info("Thread-interruptTwo is running...");
                //通过判断打断标记结束当前线程
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.info("interrupted...,end loop");
                    break;
                }
            }
        }, "Thread-interruptTwo");
        threadInterruptTwo.start();
        TimeUnit.SECONDS.sleep(1);
        //打断运行的线程在程序运行中并不会直接停止线程的运行，只是告知线程被打断过，需要停止运行或者其他操作需要在线程中去操作
        threadInterruptTwo.interrupt();
    }

    /**
     * 配合park使用interrupted()判断方法
     */
    @Test
    public void staticInterruptedUsage() throws InterruptedException {
        Thread threadPark = new Thread(() -> {
            log.info("start execution Thread-Park.LockSupport.park()...");
            //park会让正在运行的线程暂停运行
            LockSupport.park();
            //调用了interrupted()后会将状态重置为false，若是调用isInterrupted()为true的时候，再次执行park会使park失效，所以结合interrupted使用
            log.info("First park after... | interrupt status:{}", Thread.interrupted());
            log.info("Thread.interrupted() after... | interrupt status:{}", Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.info("Second park after...");
        }, "Thread-Park");

        threadPark.start();
        TimeUnit.SECONDS.sleep(2);
        //打断park
        threadPark.interrupt();
    }

    /**
     * Two Phase Termination 设计模式：两阶段终止模式(interrupt的优雅实现）
     * 在一个线程T1中，终止线程T2做到优雅的终止线程T2，这里所谓的“优雅”，指的是给T2一个机会料理后事，而不是被一剑封喉。
     * 错误思路：
     * Java语言的Thread类中曾经提供了一个stop()方法，用来终止线程，可是早已不建议使用了，原因是这个方法用的就是一剑封喉的做法，被终止的线程没有机会料理后事。
     * 1、使用线程对象的stop()方法停止线程
     * stop方法会真正杀死线程，如果这时线程锁住了共享资源，那么当它被杀死后就再也没有机会释放锁，其它线程将永远无法获取锁
     * 2、使用System.exit(int)方法停止线程
     * 目的仅是停止一个线程，但这种做法会让整个程序都停止
     */
    @Test
    public void twoPhaseTermination() throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        TimeUnit.MILLISECONDS.sleep(5000);
        twoPhaseTermination.stop();
    }

    /**
     * 不推荐使用的方法，这些方法已过时，容易破坏同步代码块，造成线程死锁
     * stop & suspend & resume
     * stop():停止线程运行
     * suspend:挂起（暂停)线程运行
     * resume:恢复线程运行
     */
    public void stopAndSuspendAndResume() {

    }

    /**
     * while(true)时通过睡眠一会儿防止CPU占满
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadCommonMethod threadCommonMethod = new ThreadCommonMethod();
//        threadCommonMethod.sleepCase();

        threadCommonMethod.interruptUsage();
    }
}
