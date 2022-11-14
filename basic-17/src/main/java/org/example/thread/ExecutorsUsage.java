package org.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * purpose:Executors线程工具类
 *
 * @author Pan Liuyang
 * 2022/11/14 15:05
 * public static ExecutorService newCachedThreadPool()线程数量随着任务增加而增加，如果线程任务执行完毕且空闲了一段时间则会被回收掉。
 * public static ExecutorService newFixedThreadPool(int nThreads)创建固定线程数量的线程池，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程替代它。
 * public static ExecutorService newSingleThreadExecutor ()创建只有一个线程的线程池对象，如果该线程出现异常而结束，那么线程池会补充一个新线程。
 * public static scheduledExecutorService newScheduledThreadPool(int corePoo)创建一个线程池，可以实现在给定的延迟后运行任务，或者定期执行任务。
 */
public class ExecutorsUsage {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
    }
}
