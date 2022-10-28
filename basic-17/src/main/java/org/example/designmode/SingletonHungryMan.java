package org.example.designmode;

/**
 * 饿汉单例模式：类加载时会先在堆内存中创建一个实例
 * 线程安全的
 */
public class SingletonHungryMan {
    public static SingletonHungryMan singletonHungryMan = new SingletonHungryMan();

    private SingletonHungryMan() {
    }
}
