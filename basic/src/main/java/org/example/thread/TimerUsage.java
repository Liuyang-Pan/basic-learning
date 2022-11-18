package org.example.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * purpose:定时器使用
 *
 * @author Pan Liuyang
 * 2022/11/14 15:15
 */
public class TimerUsage {
    public static void main(String[] args) {
        //Timer单线程执行，多个定时器可能出现问题
        Timer timer = new Timer();
        //延迟3S后执行，然后每2秒执行一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器执行了");
            }
        }, 3000, 2000);

        //内部为线程池的定时器
        ScheduledExecutorService timerSchedule = Executors.newScheduledThreadPool(3);
        timerSchedule.scheduleAtFixedRate(() -> System.out.println("ScheduledExecutorService定时器执行了"), 2, 3, TimeUnit.SECONDS);
    }
}
