package org.example.innerclass;

public class AnonymousInnerClass {


    public static void main(String[] args) {
        Animal animal = new Animal() {
            @Override
            public void run() {
                System.out.println("Animal is running...");
            }
        };
        animal.run();

        Swimming swimming = new Swimming() {
            @Override
            public void swim() {
                System.out.println("Swimming...");
            }
        };
        swimming.swim();
    }
}

abstract class Animal {
    public abstract void run();
}

class Tiger extends Animal {

    @Override
    public void run() {
        System.out.println("Tiger is running...");
    }
}

interface Swimming {
    void swim();
}