package org.example.shared.object;

import lombok.Data;

/**
 * purpose:面向对象思想同步操作结果
 *
 * @author Pan Liuyang
 * 2022/10/12 10:19
 */
@Data
public class SynchronizedResult {

    private int result = 0;

    public void addition() {
        synchronized (this) {
            for (int i = 0; i < 500000; i++)
                result++;
        }
    }

    public void subtraction() {
        synchronized (this) {
            for (int i = 0; i < 500000; i++)
                result--;
        }
    }

    public int getSynchronizedResult() {
        synchronized (this) {
            return result;
        }
    }

    public int getResultTest() {
        return result;
    }
}
