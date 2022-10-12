package org.example.shared;

import lombok.extern.slf4j.Slf4j;

/**
 * purpose:共享变量的操作
 *
 * @author Pan Liuyang
 * 2022/10/11 17:38
 */
@Slf4j
public class SharedVariableOperation {
    static int result = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread threadAddition = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                result++;
            }
        }, "Thread-Addition");
        Thread threadSubtraction = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                result--;
            }
        }, "Thread-Subtraction");
        threadAddition.start();
        threadSubtraction.start();
        threadAddition.join();
        threadSubtraction.join();
        log.info("result = {}", result);
    }
}
