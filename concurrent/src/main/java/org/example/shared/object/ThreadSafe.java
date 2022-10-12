package org.example.shared.object;

import java.util.ArrayList;
import java.util.List;

/**
 * purpose:安全的操作线程
 * list作为方法一的局部变量，每一个线程都会在堆内存中创建一个list，各线程用自己的，不会共享
 *
 * @author Pan Liuyang
 * 2022/10/12 15:32
 */
public class ThreadSafe {

    public void methodOne() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            methodTwo(list);
            methodThree(list);
        }
    }

    public void methodTwo(List<String> list) {
        list.add("methodTwo");
    }

    public void methodThree(List<String> list) {
        list.remove(0);
    }
}
