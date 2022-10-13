package org.example.shared.object;

import lombok.Data;

/**
 * purpose:售票案例-售票窗口对象
 *
 * @author Pan Liuyang
 * 2022/10/13 9:21
 */
@Data
public class TicketWindow {
    /**
     * 余票数量
     */
    private int numberOfRemainingVotes;

    /**
     * 构造方法-传入余票数
     *
     * @param number 余票数
     */
    public TicketWindow(int number) {
        this.numberOfRemainingVotes = number;
    }

    /**
     * 售票操作
     *
     * @param number 售票数量
     * @return 买到几张票
     */
    public synchronized int ticketSales(int number) {
        if (numberOfRemainingVotes >= number) {
            numberOfRemainingVotes -= number;
            return number;
        } else {
            return 0;
        }
    }
}
