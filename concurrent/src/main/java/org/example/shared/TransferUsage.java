package org.example.shared;

import lombok.extern.slf4j.Slf4j;
import org.example.shared.object.Account;

import java.util.Random;

/**
 * purpose:转账示例
 *
 * @author Pan Liuyang
 * 2022/10/13 16:01
 */
@Slf4j
public class TransferUsage {

    private static Random random = new Random();

    private static int randomNumber() {
        return random.nextInt(5) + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        Account accountOne = new Account(2000);
        Account accountTwo = new Account(2000);

        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                accountOne.transfer(accountTwo, randomNumber());
            }
        });
        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                accountTwo.transfer(accountOne, randomNumber());
            }
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        log.info("accountOne:{}", accountOne.getBalance());
        log.info("accountTwo:{}", accountTwo.getBalance());
        log.info("total:{}", accountOne.getBalance() + accountTwo.getBalance());
    }
}
