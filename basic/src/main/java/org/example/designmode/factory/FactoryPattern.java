package org.example.designmode.factory;

/**
 * purpose:电脑工厂
 *
 * @author Pan Liuyang
 * 2022/11/17 16:50
 */
public class FactoryPattern {

    public static Computer createComputer(String info) {
        switch (info) {
            case "mac":
                Computer mac = new Mac();
                mac.setName("Apple MacBook Pro");
                mac.setPrice(19999.99);
                return mac;
            case "lenovo":
                Computer lenovo = new Lenovo();
                lenovo.setName("Lenovo ThinkBook");
                lenovo.setPrice(12999.99);
                return lenovo;
        }
        return null;
    }
}
