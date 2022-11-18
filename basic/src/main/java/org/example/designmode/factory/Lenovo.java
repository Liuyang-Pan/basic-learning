package org.example.designmode.factory;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/17 16:52
 */
public class Lenovo extends Computer {

    public Lenovo() {
    }

    public Lenovo(String name, double price) {
        super(name, price);
    }

    @Override
    public void start() {
        super.start();
        System.out.println(getName() + "开始启动...");
        System.out.println("显示Lenovo Logo...");
        System.out.println("进入主界面");
    }
}
