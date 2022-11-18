package org.example.polymorphic;

public class Tortoise extends Animal {
    public String name = "乌龟";

    @Override
    public void run() {
        System.out.println("Tortoise is running...");
    }

    public void layEggs() {
        System.out.println("Laying eggs...");
    }
}
