package org.example.polymorphic;

public class Test {
    public static void main(String[] args) {
        /*
         多态：同类型的对象，执行同一个行为，会表现出不同的行为特征。
         多态的常见形式
         1、父类类型对象名称 = new子类构造器;
         2、接口对象名称 = new 实现类构造器;
         */
        System.out.println("-----------------基本使用-----------------");
        {
            Animal dog = new Dog();
            Animal tortoise = new Tortoise();
            //方法调用：编译看左边，运行看右边
            dog.run();
            System.out.println("--------------");
            tortoise.run();

            //变量调用：编译看左边，运行也看左边（多态侧重行为多态）
            System.out.println(dog.name);
            System.out.println("--------------");
            System.out.println(tortoise.name);
        }
        System.out.println("-----------------类型转换-----------------");
        {
            //自动类型转换
            Animal shiftDog = new Dog();
            shiftDog.run();
            //强制类型转换 可以调用子类独有的方法
            Animal castTortoise = new Tortoise();
            castTortoise.run();
            //强制转换通过instanceof先判断对象类型 强制类型转换时编译不报错，运行时报错ClassCastException
            if (castTortoise instanceof Tortoise) {
                Tortoise tortoise = (Tortoise) castTortoise;
                tortoise.layEggs();
            }
        }

    }
}
