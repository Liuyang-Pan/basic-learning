package org.example.shared;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * purpose:synchronized优化原理进阶
 * <p>
 * 1.轻量级锁-为锁对象交换地址更改状态 不采用Monitor锁
 * 轻量级锁的使用场景:如果一个对象虽然有多线程访问，但多线程访问的时间是错开的（也就是没有竞争)，那么可以使用轻量级锁来优化。
 * 轻量级锁对使用者是透明的，即语法仍然是synchronized
 * <p>
 * 2.锁膨胀-存在竞争时加上Monitor锁
 * 如果在尝试加轻量级锁的过程中，CAS（与锁对象交换地址和状态的操作）操作无法成功，这时一种情况就是有其它线程为此对象加上了轻量级锁(有竞争)，<br>
 * 这时需要进行锁膨胀，将轻量级锁变为重量级锁。
 * <p>
 * 3.自旋优化-已加上重量级锁，后进入的线程进行自旋（重试看看之前的线程是否已经释放锁，若是重试几次还是没有，就进入阻塞状态）【适用于多核CPU才有意义】
 * 重量级锁竞争的时候，还可以使用自旋来进行优化，如果当前线程自旋成功(即这时候持锁线程已经退出了同步块，释放了锁)，这时当前线程就可以避免阻塞。
 * <p>
 * 4.偏向锁
 * 轻量级锁在没有竞争时(就自己这个线程)，每次重入仍然需要执行CAS操作。
 * Java 6中引入了偏向锁来做进一步优化:只有第一次使用CAS将线程ID设置到对象的 Mark Word头，之后发现这个线程ID是自己的就表示没有竞争，不用重新CAS。以后只要不发生竞争，这个对象就归该线程所有。
 * 对象创建时:
 * 如果开启了偏向锁（默认开启)，那么对象创建后，mark word值为0x05即最后3位为101，这时它的thread、epoch、age都为О
 * 偏向锁是默认是延迟的，不会在程序启动时立即生效，如果想避免延迟，可以加VM参数-XX:BiasedLockingStartupDelay=0来禁用延迟
 * 如果没有开启偏向锁，那么对象创建后，mark word值为0x01即最后3位为001，这时它的hashcode、age都为0，第一次用到hashcode时才会赋值
 * VM参数-XX:-UseBiasedLocking 禁用偏向锁 -XX:+UseBiasedLocking 开启偏向锁
 *
 * @author Pan Liuyang
 * 2022/10/13 17:22
 */
public class SynchronizedOptimization {
    static final Object lock = new Object();

    /**
     * 同一个线程方法1调用方法2重入锁（重入锁会让锁对象的Mark Word对象头再次做CAS操作，会有性能的损耗）
     * 偏向锁对重入锁进行了优化，将ThreadId记录到对象头中
     */
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

class BiasObject {

}

/**
 * 偏向锁
 */
@Slf4j
class ThreadBiasLock {
    public static void main(String[] args) throws InterruptedException {
        BiasObject biasObject = new BiasObject();
        //调用hashCode会撤销偏向锁，因为对象头存不下偏向锁的线程ID了。调用wait和notify也会撤销偏向锁，因为wait和notify只有重量级锁才有
//        biasObject.hashCode();
        String biasObjectClassLayout = ClassLayout.parseInstance(biasObject).toPrintable();
        log.info(biasObjectClassLayout);

        synchronized (biasObject) {
            log.info(biasObjectClassLayout);
        }

        synchronized (biasObject) {
            log.info(biasObjectClassLayout);
        }

        log.info(biasObjectClassLayout);

        TimeUnit.SECONDS.sleep(4);
        //偏向锁延迟
        String biasObjectClassLayoutNew = ClassLayout.parseInstance(new BiasObject()).toPrintable();
        log.info(biasObjectClassLayoutNew);
    }
}
