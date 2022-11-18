package org.example.designmode.factory;

import org.junit.jupiter.api.Test;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/17 16:47
 */
public class FactoryTest {

    @Test
    public void test() {
        {
            //非工厂方式创建对象
            Computer mac = new Mac("Apple Mac Pro", 19999.99);
            mac.start();
        }
        {
            //工厂方式创建对象
            Computer lenovo = FactoryPattern.createComputer("lenovo");
            if (null != lenovo) lenovo.start();
        }
        {
            //工厂方式创建对象
            Computer macBook = FactoryPattern.createComputer("mac");
            if (null != macBook) macBook.start();
        }
    }
}
