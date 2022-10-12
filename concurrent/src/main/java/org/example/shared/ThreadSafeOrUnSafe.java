package org.example.shared;

import org.example.shared.object.ThreadSafe;
import org.example.shared.object.ThreadSafeSubclass;
import org.example.shared.object.ThreadUnsafe;

/**
 * purpose:线程是否安全<br>
 * 常见线程安全类：String、Integer、StringBuffer、Random、Vector、Hashtable、java.util.concurrent包下的类
 * 组合调用线程安全类中的方法不一定线程安全，若组合调用需要线程安全还是需要加synchronized上锁
 * String、Integer等都是不可变类，因为其内部的状态不可以改变，因此它们的方法都是线程安全的
 * 对不可变类的数据进行修改的时候会新建一个对象复制一份
 *
 * @author Pan Liuyang
 * 2022/10/12 15:25
 */
public class ThreadSafeOrUnSafe {

    public static void main(String[] args) {
        //线程安全的操作
        ThreadSafe threadSafe = new ThreadSafe();
        for (int i = 0; i < 5; i++) {
            new Thread(threadSafe::methodOne).start();
        }
        //线程不安全的操作
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < 5; i++) {
            new Thread(threadUnsafe::methodOne).start();
        }
        //继承重写了方法三新开了线程去操作了局部变量，线程也不安全
        ThreadSafeSubclass threadSafeSubclass = new ThreadSafeSubclass();
        for (int i = 0; i < 20; i++) {
            new Thread(threadSafeSubclass::methodOne).start();
        }
    }
}
