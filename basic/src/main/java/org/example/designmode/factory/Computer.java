package org.example.designmode.factory;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/17 16:45
 */
public class Computer {
    private String name;
    private double price;

    public Computer() {
    }

    public Computer(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void start() {
        System.out.println("开机");
    }
}
