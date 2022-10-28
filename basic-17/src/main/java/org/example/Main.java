package org.example;

import org.example.designmode.SingletonHungryMan;
import org.example.designmode.SingletonSluggard;

public class Main {
    public static void main(String[] args) {
        singletonHungryManTest();
        singletonSluggardTest();
    }

    private static void singletonHungryManTest() {
        SingletonHungryMan singletonHungryMan = SingletonHungryMan.singletonHungryMan;
        SingletonHungryMan singletonHungryMan1 = SingletonHungryMan.singletonHungryMan;
        System.out.println(singletonHungryMan == singletonHungryMan1);
    }

    private static void singletonSluggardTest() {
        SingletonSluggard singletonSluggard = SingletonSluggard.getInstance();
        SingletonSluggard singletonSluggard1 = SingletonSluggard.getInstance();
        System.out.println(singletonSluggard == singletonSluggard1);
    }
}