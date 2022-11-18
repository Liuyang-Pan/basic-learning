package org.example.polymorphic;

public class Dog extends Animal {
    public String name = "狗";

    @Override
    public void run() {
        System.out.println("Dog is running...");
    }

    public void lookDoor() {
        System.out.println("Watching...");
    }
}
