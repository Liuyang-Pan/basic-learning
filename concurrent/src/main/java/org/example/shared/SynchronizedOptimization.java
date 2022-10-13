package org.example.shared;

/**
 * purpose:synchronized优化原理进阶
 * <p>
 * 1.轻量级锁-为锁对象交换地址更改状态 不采用Monitor锁
 * 轻量级锁的使用场景:如果一个对象虽然有多线程访问，但多线程访问的时间是错开的（也就是没有竞争)，那么可以使用轻量级锁来优化。
 * 轻量级锁对使用者是透明的，即语法仍然是synchronized
 * <p>
 * 2.锁膨胀-存在竞争时加上Monitor锁
 * 如果在尝试加轻量级锁的过程中，CAS（与锁对象交换状态的操作）操作无法成功，这时一种情况就是有其它线程为此对象加上了轻量级锁(有竞争)，<br>
 * 这时需要进行锁膨胀，将轻量级锁变为重量级锁。
 * <p>
 * 3.自旋优化-已加上重量级锁，后进入的线程进行自旋（重试看看之前的线程是否已经释放锁，若是重试几次还是没有，就进入阻塞状态）【适用于多核CPU才有意义】
 * 重量级锁竞争的时候，还可以使用自旋来进行优化，如果当前线程自旋成功(即这时候持锁线程已经退出了同步块，释放了锁)，这时当前线程就可以避免阻塞。
 * <p>
 * 4.偏向锁
 * 轻量级锁在没有竞争时(就自己这个线程)，每次重入仍然需要执行CAS操作。
 * Java 6中引入了偏向锁来做进一步优化:只有第一次使用CAS将线程ID设置到对象的Mark Word头，之后发现这个线程D是自己的就表示没有竞争，不用重新CAS。以后只要不发生竞争，这个对象就归该线程所有
 *
 * @author Pan Liuyang
 * 2022/10/13 17:22
 */
public class SynchronizedOptimization {
    static final Object lock = new Object();

    public static void method1() {
        synchronized (lock) {
            //同步块A
            method2();
        }
    }

    public static void method2() {
        synchronized (lock) {
            //同步块B
        }
    }
}
