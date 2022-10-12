package org.example.shared.object;

import java.util.ArrayList;
import java.util.List;

/**
 * purpose:不安全的操作线程
 * list作为当前类的成员变量，新建一个对象操作同一个对象的方法会操作堆内存中的同一个list。<br>
 * 当多个线程进行操作同一个对象的成员变量时，这个成员变量就是一个共享的变量，存在线程安全问题
 *
 * @author Pan Liuyang
 * 2022/10/12 15:21
 */
public class ThreadUnsafe {
    private List<String> list = new ArrayList<>();

    public void methodOne() {
        for (int i = 0; i < 100; i++) {
            methodTwo();
            methodThree();
        }
    }

    private void methodTwo() {
        list.add("methodTwo");
    }

    private void methodThree() {
        list.remove(0);
    }
}
