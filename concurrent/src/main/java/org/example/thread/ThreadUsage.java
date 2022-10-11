package org.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * purpose:创建线程的基础使用方式
 *
 * @author Pan Liuyang
 * 2022/9/30 17:11
 */
@Slf4j
public class ThreadUsage {

    /**
     * 继承线程类的方式创建线程
     */
    static class ThreadExtends extends Thread {
        @Override
        public void run() {
            log.info("继承线程");
        }
    }

    @Test
    public void threadUsage() {
        //使用匿名内部类方式创建线程
        Thread threadAnonymous = new Thread() {
            @Override
            public void run() {
                //线程执行的内容
                log.info("匿名内部类线程");
            }
        };
        Thread threadLambda = new Thread(() ->
                //线程执行的内容
                log.info("Lambda线程")
        );
        //启动线程
        threadAnonymous.setName("Thread-Anonymous");
        threadAnonymous.start();
        threadLambda.setName("Thread-Lambda");
        threadLambda.start();
        ThreadExtends threadExtends = new ThreadExtends();
        threadExtends.setName("Thread-Extends");
        threadExtends.start();
    }
}
