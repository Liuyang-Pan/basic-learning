package org.example.abstractclass;

/**
 * 抽象类
 * 注意事项：
 * 1、抽象方法只有方法签名，不能声明方法体。
 * 2、一个类中如果定义了抽象方法，这个类必须声明成抽象类，否则报错。
 */
public abstract class Animal {

    /**
     * 抽象方法：不包含方法体
     */
    public abstract void run();
}
