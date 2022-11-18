package org.example.designmode.singleton;

/**
 * 懒汉单例模式：在调用getInstance方法时才在堆内存中创建一个实例
 * 非线程安全的，需要加synchronized关键字
 */
public class SingletonSluggard {
    private static SingletonSluggard singletonSluggard;

    /**
     * 若不加synchronized则是非线程安全的
     *
     * @return SingletonSluggard实例
     */
    public static synchronized SingletonSluggard getInstance() {
        if (null == singletonSluggard) {
            singletonSluggard = new SingletonSluggard();
        }
        return singletonSluggard;
    }

    private SingletonSluggard() {
    }
}
