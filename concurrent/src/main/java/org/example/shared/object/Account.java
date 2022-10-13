package org.example.shared.object;

import lombok.Data;

/**
 * purpose:转账-账户对象
 *
 * @author Pan Liuyang
 * 2022/10/13 15:53
 */
@Data
public class Account {
    /**
     * 账户余额
     */
    private int balance;

    /**
     * 构造方法-初始值
     *
     * @param money 账户初始额
     */
    public Account(int money) {
        this.balance = money;
    }

    /**
     * 转账操作
     *
     * @param target 目标账户
     * @param money  转账金额
     */
    public void transfer(Account target, int money) {
        //简单使用
        synchronized (Account.class) {
            if (balance >= money) {
                balance -= money;
                target.balance += money;
            }
        }
    }
}
