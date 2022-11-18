package org.example.thread;

import java.util.concurrent.*;

/**
 * purpose:线程池使用
 *
 * @author Pan Liuyang
 * 2022/11/14 09:55
 * ThreadPoolExecutor:
 * int corePoolSize:指定线程池的线程数量（核心线程),不能小于0
 * int maximumPoolSize:指定线程池可支持的最大线程数,最大数量>=核心线程数量
 * long keepAliveTime:指定临时线程的最大存活时间,不能小于0
 * TimeUnit unit:指定存活时间的单位(秒、分、时、天),时间单位
 * BlockingQueue<Runnable> workQueue:指定任务队列,不能为null
 * ThreadFactory threadFactory:指定用哪个线程工厂创建线程,不能为null
 * RejectedExecutionHandler handler:指定线程忙，任务满的时候，新任务来了怎么办,不能为null
 * -ThreadPoolExecutor.AbortPolicy 丢弃任务并抛出RejectedExecutionException异常。是默认的策略
 * -ThreadPoolExecutor.DiscardPolicy 丢弃任务，但是不抛出异常这是不推荐的做法
 * -ThreadPoolExecutor.DiscardOldestPolicy 抛弃队列中等待最久的任务然后把当前任务加入队列中
 * -ThreadPoolExecutor.CallerRunsPolicy 由主线程负责调用任务的run()方法从而绕过线程池直接执行
 */
public class ThreadPoolUsage {
    public static void main(String[] args) {
        /*
            核心线程数2、最大线程数5、最大空闲存活时间10、存活时间单位秒、5个缓存任务队列、默认创建线程工厂、默认规则
            由于有5个缓存任务队列，若执行七个线程任务还是2个线程跑，因为未超出缓存的任务队列，若是八个则会开始创建

         */
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        MyRunnable task = new MyRunnable();
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        //ThreadPoolExecutor.AbortPolicy由于最大线程数为5，缓存队列也为5，总共同时可接收10个任务，超出会抛出异常RejectedExecutionException拒绝执行
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        threadPool.execute(task);
        //立即关闭线程池，即使有任务执行
//        threadPool.shutdownNow();
        //任务执行完毕后关闭线程池
//        threadPool.shutdown();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is running..." + "Cycles" + i);
        }
    }
}

class CallablePool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        Future<String> submit = threadPool.submit(new MyCallable(100));
        try {
            System.out.println(submit.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<String> {
    private int n;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return Thread.currentThread().getName() + "    1-" + n + "的和为:" + sum;
    }
}