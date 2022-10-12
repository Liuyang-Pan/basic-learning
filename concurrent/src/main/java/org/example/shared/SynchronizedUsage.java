package org.example.shared;

import lombok.extern.slf4j.Slf4j;
import org.example.shared.object.SynchronizedResult;
import org.junit.jupiter.api.Test;

/**
 * purpose:synchronized 解决方案 synchronized只能锁对象<br>
 * 两种写法都是锁当前对象 synchronized(this){} = public synchronized void test(){}<br>
 * 两种写法都是锁类对象synchronized(Test.class){} = public synchronized static void test(){}<br>
 * <p>
 * 互斥 <br>
 * 为了避免临界区的竞态条件发生，有多种手段可以达到目的。<br>
 * 1、阻塞式的解决方案: synchronized，Lock<br>
 * 2、非阻塞式的解决方案:原子变量
 * <p>
 * 本类使用阻塞式的解决方案: synchronized，来解决上述问题，即俗称的【对象锁】，它采用互斥的方式让同一时刻至多只有一个线程能持有【对象锁】，其它线程再想获取这个【对象锁】时就会阻塞住。<br>
 * 这样就能保证拥有锁的线程可以安全的执行临界区内的代码，不用担心线程上下文切换
 * <p>
 * [注意]
 * 虽然java中互斥和同步都可以采用synchronized关键字来完成，但它们还是有区别的:<br>
 * 1、互斥是保证临界区的竞态条件发生，同一时刻只能有一个线程执行临界区代码<br>
 * 2、同步是由于线程执行的先后、顺序不同、需要一个线程等待其它线程运行到某个点<br>
 *
 * @author Pan Liuyang
 * 2022/10/12 9:49
 */
@Slf4j
public class SynchronizedUsage {

    //共享变量
    static int result = 0;
    //定义锁对象
    static Object lock = new Object();

    /**
     * Synchronize用法
     *
     * @throws InterruptedException 打断异常
     */
    @Test
    public void mainSynchronizeUsage() throws InterruptedException {
        SynchronizedResult synchronizedResult = new SynchronizedResult();
        Thread threadSynchronizedAddition = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //多个线程操作同一个临界区（对共享变量做读写操作）锁对象必须一致，不然锁无效，结果也无法预测
                synchronized (lock) {
                    result++;
                }

            }
            synchronizedResult.addition();
        }, "Thread-Addition");
        Thread threadSynchronizedSubtraction = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    result--;
                }
            }
            synchronizedResult.subtraction();
        }, "Thread-Subtraction");
        threadSynchronizedAddition.start();
        threadSynchronizedSubtraction.start();
        log.info("result = {}", synchronizedResult.getResult());
        log.info("SynchronizedResult = {}", synchronizedResult.getSynchronizedResult());
        log.info("" + synchronizedResult.getResultTest());
        threadSynchronizedAddition.join();
        threadSynchronizedSubtraction.join();
        log.info("result = {}", result);
        log.info("SynchronizedResult.getResult({})", synchronizedResult.getResult());
    }
}
