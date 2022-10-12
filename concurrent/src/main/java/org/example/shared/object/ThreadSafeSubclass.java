package org.example.shared.object;

import java.util.List;

/**
 * purpose:重新开一个线程去操作list会有共享操作问题出现
 *
 * @author Pan Liuyang
 * 2022/10/12 15:44
 */
public class ThreadSafeSubclass extends ThreadSafe {

    @Override
    public void methodThree(List<String> list) {
        new Thread(() -> list.remove(0)).start();
    }
}
