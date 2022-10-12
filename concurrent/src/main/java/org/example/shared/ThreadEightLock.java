package org.example.shared;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * purpose:线程八锁
 *
 * @author Pan Liuyang
 * 2022/10/12 11:18
 */
@Slf4j
public class ThreadEightLock {
}

/**
 * 输出结果可能先1后2 或者先2后1,锁同一个对象，互斥
 */
@Slf4j
class LockOne {
    //锁当前对象
    public synchronized void methodOne() {
        log.info("LockOne.methodOne()");
    }

    public synchronized void methodTwo() {
        log.info("LockOne.methodTwo()");
    }

    @Test
    public void test() {
        LockOne lockOne = new LockOne();
        new Thread(() -> lockOne.methodOne()).start();
        new Thread(() -> lockOne.methodTwo()).start();
    }
}

/**
 * 输出结果可能先睡眠1秒后打印1后2 或者先打印2再睡眠1秒后打印1，互斥
 */
@Slf4j
class LockTwo {
    //锁当前对象
    public synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockTwo.methodOne()");
    }

    public synchronized void methodTwo() {
        log.info("LockTwo.methodTwo()");
    }

    @Test
    public void test() {
        LockTwo lockTwo = new LockTwo();
        new Thread(() -> {
            try {
                lockTwo.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> lockTwo.methodTwo()).start();
    }
}

/**
 * 方法三不会出现互斥效果，会并行执行方法三。
 */
@Slf4j
class LockThree {
    //锁当前对象
    public synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockThree.methodOne()");
    }

    public synchronized void methodTwo() {
        log.info("LockThree.methodTwo()");
    }

    public void methodThree() {
        log.info("LockThree.methodThree()");
    }

    @Test
    public void test() {
        LockThree lockThree = new LockThree();
        new Thread(() -> {
            try {
                lockThree.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> lockThree.methodTwo()).start();
        new Thread(() -> lockThree.methodThree()).start();
    }
}

/**
 * 创建不同对象去执行方法也不互斥
 */
@Slf4j
class LockFour {
    //锁当前对象
    public synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockFour.methodOne()");
    }

    public synchronized void methodTwo() {
        log.info("LockFour.methodTwo()");
    }

    @Test
    public void test() {
        LockFour lockFourOne = new LockFour();
        LockFour lockFourTwo = new LockFour();
        new Thread(() -> {
            try {
                lockFourOne.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(lockFourTwo::methodTwo).start();
    }
}

/**
 * methodOne锁的是LockFive.class,methodTwo锁住的是new LockFive()对象，不互斥
 */
@Slf4j
class LockFive {
    public static synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockFive.methodOne()");
    }

    public synchronized void methodTwo() {
        log.info("LockFive.methodTwo()");
    }

    @Test
    public void test() {
        LockFive lockFiveOne = new LockFive();
        LockFive lockFiveTwo = new LockFive();
        new Thread(() -> {
            try {
                lockFiveOne.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(lockFiveTwo::methodTwo).start();
    }
}

/**
 * methodOne和methodTwo锁的是LockFive.class,LockFive.class仅有一个，互斥
 */
@Slf4j
class LockSix {
    public static synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockSix.methodOne()");
    }

    public static synchronized void methodTwo() {
        log.info("LockSix.methodTwo()");
    }

    @Test
    public void test() {
        LockSix lockSix = new LockSix();
        new Thread(() -> {
            try {
                lockSix.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> lockSix.methodTwo()).start();
    }
}

/**
 * 不是锁的同一个对象，不互斥
 */
@Slf4j
class LockSeven {
    //锁当前对象
    public static synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockSeven.methodOne()");
    }

    public synchronized void methodTwo() {
        log.info("LockSeven.methodTwo()");
    }

    @Test
    public void test() {
        LockSeven lockSevenOne = new LockSeven();
        LockSeven lockSevenTwo = new LockSeven();
        new Thread(() -> {
            try {
                lockSevenOne.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(lockSevenTwo::methodTwo).start();
    }
}

/**
 * 锁的是LockEight.class，互斥
 */
@Slf4j
class LockEight {
    //锁当前对象
    public static synchronized void methodOne() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("LockEight.methodOne()");
    }

    public static synchronized void methodTwo() {
        log.info("LockEight.methodTwo()");
    }

    @Test
    public void test() {
        LockEight lockEightOne = new LockEight();
        LockEight lockEightTwo = new LockEight();
        new Thread(() -> {
            try {
                lockEightOne.methodOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> lockEightTwo.methodTwo()).start();
    }
}