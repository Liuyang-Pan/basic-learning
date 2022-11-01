package org.example.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * 首先必须是接口、其次接口中有且仅有一个抽象方法的形式
 * 通常我们会在接口上加上一个@FunctionalInterface注解，标记该接口必须是满足函数式接口。
 */
public class LambdaDemo {
    public static void main(String[] args) {
        Animal animal = new Animal() {
            @Override
            public void run() {
                System.out.println("Running...");
            }
        };
        animal.run();

        Swim swim = () -> System.out.println("swimming...");
    }

    public void go(Swim swim) {

    }
}

@FunctionalInterface
interface Swim {
    void swimming();
}

//仅能接口使用
abstract class Animal {
    public abstract void run();
}
