package org.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/9/30 17:02
 */
public class ThreadTest extends Thread {

    @Override
    public void run() {
        System.out.println(String.valueOf(new Date()));
        for (int i = 0; i < 1000000000; i++) {
            int j = 10;
        }
        System.out.println(String.valueOf(new Date()));
    }
}
