package org.example.callable;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * purpose:通过Callable创建线程使用的基础使用方式
 *
 * @author Pan Liuyang
 * 2022/10/9 17:22
 */
@Slf4j(topic = "Callable")
public class CallableUsage {

    @Test
    public void callableUsage() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("running...");
                Thread.sleep(5000);
                return 100;
            }
        });

        Thread thread = new Thread(task, "Thread-Callable");
        thread.start();
        log.info(task.get().toString());
    }
}