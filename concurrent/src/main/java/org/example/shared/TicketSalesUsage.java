package org.example.shared;

import lombok.extern.slf4j.Slf4j;
import org.example.shared.object.TicketWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * purpose:售票使用
 *
 * @author Pan Liuyang
 * 2022/10/13 9:26
 */
@Slf4j
public class TicketSalesUsage {
    private static Random random = new Random();

    private static int randomNumber() {
        return random.nextInt(5) + 1;
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(1000);
        //线程集合
        List<Thread> threadList = new ArrayList<>();
        //售票总数
        List<Integer> totalNumberOfTicketsSold = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                int result = ticketWindow.ticketSales(randomNumber());
                totalNumberOfTicketsSold.add(result);
            });
            threadList.add(thread);
            thread.start();
        }

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("numberOfRemainingVotes:{}", ticketWindow.getNumberOfRemainingVotes());
        log.info("totalNumberOfTicketsSold:{}", totalNumberOfTicketsSold.stream().mapToInt(i -> i).sum());
    }
}
