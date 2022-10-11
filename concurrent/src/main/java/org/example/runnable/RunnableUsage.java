package org.example.runnable;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * purpose:通过Runnable创建线程的基础使用方式
 *
 * @author Pan Liuyang
 * 2022/10/9 16:51
 */
@Slf4j(topic = "Runnable")
public class RunnableUsage {

    /**
     * 实现Runnable的方式
     */
    static class RunnableImpl implements Runnable {

        @Override
        public void run() {

            log.info("Thread-Runnable_Implements");
        }
    }

    @Test
    public void runnableUsage() {

        //匿名内部类方式
        Runnable runnableAnonymous = new Runnable() {
            @Override
            public void run() {
                log.info("Thread-Runnable_Anonymous");
            }
        };

        //Lambda方式
        Runnable runnableLambda = () -> log.info("Thread-Runnable_Lambda");

        Thread threadAnonymous = new Thread(runnableAnonymous, "Thread-Anonymous");
        threadAnonymous.start();
        Thread threadLambda = new Thread(runnableLambda, "Thread-Lambda");
        threadLambda.start();
        RunnableImpl runnable = new RunnableImpl();
        Thread threadImplements = new Thread(runnable, "Thread-Implements");
        threadImplements.start();
    }
}
