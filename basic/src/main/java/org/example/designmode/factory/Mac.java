package org.example.designmode.factory;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/17 16:46
 */
public class Mac extends Computer {

    public Mac() {
    }

    public Mac(String name, double price) {
        super(name, price);
    }

    @Override
    public void start() {
        super.start();
        System.out.println(getName() + "开始启动...");
        System.out.println("显示Apple Logo...");
        System.out.println("进入主界面");
    }
}
