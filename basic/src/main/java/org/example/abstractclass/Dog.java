package org.example.abstractclass;

/**
 * 继承抽象类Animal
 */
public class Dog extends Animal {

    /**
     * 必须实现重写run方法
     */
    @Override
    public void run() {
        System.out.println("Dog is running...");
    }
}
