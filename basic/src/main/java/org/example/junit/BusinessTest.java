package org.example.junit;

/**
 * purpose:业务
 *
 * @author Pan Liuyang
 * 2022/11/15 21:02
 */
public class BusinessTest {
    public void printHelloWorld() {
        System.out.println("HelloWorld");
    }

    public void errorMethod() {
        System.out.println(10 / 0);
    }

    public String returnString() {
        return "String";
    }
}
