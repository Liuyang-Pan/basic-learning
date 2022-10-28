package org.example.designmode;

/**
 * 懒汉单例模式：在调用getInstance方法时才在堆内存中创建一个实例
 * 非线程安全的
 */
public class SingletonSluggard {
    private static SingletonSluggard singletonSluggard;

    public static synchronized SingletonSluggard getInstance() {
        if (null == singletonSluggard) {
            singletonSluggard = new SingletonSluggard();
        }
        return singletonSluggard;
    }

    private SingletonSluggard() {
    }
}
