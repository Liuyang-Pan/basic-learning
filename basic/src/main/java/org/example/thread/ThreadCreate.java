package org.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * purpose:线程的创建
 *
 * @author Pan Liuyang
 * 2022/11/12 16:20
 */
public class ThreadCreate {
    public static void main(String[] args) {
        PrimeThread primeThread = new PrimeThread();
        primeThread.setName("CustomizeExtendsThread");
        primeThread.start();

        Thread thread = new Thread(new PrimeRun());
        thread.setName("Runnable-Thread");
        thread.start();
    }
}

/**
 * 创建方式一:继承Thread并重写run方法
 */
@Slf4j
class PrimeThread extends Thread {
    @Override
    public void run() {
        log.info("继承现场类的线程执行了...");
    }
}

/**
 * 创建方式二:实现Runnable接口
 */
@Slf4j
class PrimeRun implements Runnable {
    @Override
    public void run() {
        log.info("实现Runnable接口的线程执行了...");
    }
}

/**
 * 创建方式三:实现Callable接口并配合FutureTask启动
 */
@Slf4j
class PrimeCallable implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new PrimeCallable());
        futureTask.run();
        //get()方法会等待返回结果才执行
        System.out.println("结果" + futureTask.get());

        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("结果2" + futureTask.get());
    }

    @Override
    public String call() throws Exception {
        log.info("实现Callable接口的线程执行了...");
        return "返回的结果";
    }
}
