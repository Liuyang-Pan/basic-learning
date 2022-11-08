package org.example.throwable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Throwable下有两个异常体系
 * Error:系统级别问题、JVM退出等，代码无法控制。
 * Exception:java.lang包下，称为异常类，它表示程序本身可以处理的问题
 * -RuntimeException及其子类:运行时异常，编译阶段不会报错。(空指针异常，数组索引越界异常)
 * -除RuntimeException之外所有的异常︰编译时异常，编译期必须处理的(try)，否则程序不能通过编译。(日期格式化异常)。
 * 异常默认处理机制:
 * 1、默认会在出现异常的代码那里自动的创建一个异常对象:ArithmeticException。
 * 2、异常会从方法中出现的点这里抛出给调用者，调用者最终抛出给JVM虚拟机。
 * 3、虚拟机接收到异常对象后，先在控制台直接输出异常栈信息数据。
 * 4、直接从当前执行的异常点干掉当前程序。
 */
public class ThrowableUsage {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
//        throwsMethod();
        throwsMethodTwo("---2022-11-08 16:00:001");
    }

    /**
     * 异常处理方式1—— throws
     * throws:用在方法上，可以将方法内部出现的异常抛出去给本方法的调用者处理。
     * 这种方式并不好，发生异常的方法自己不处理异常，如果异常最终抛出去给虚拟机将引起程序死
     */
    public static void throwsMethod() throws ParseException, FileNotFoundException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse("2022-11-08 16:00:00");
        System.out.println(parse);

        FileInputStream fileInputStream = new FileInputStream("E:test");
    }

    /**
     * 异常处理方式2——try...catch...
     * 监视捕获异常，用在方法内部，可以将方法内部出现的异常直接捕获处理。
     * 这种方式还可以，发生异常的方法自己独立完成异常的处理，程序可以继续往下执行。
     *
     * @param source 字符串日期
     */
    public static void throwsMethodTwo(String source) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse(source);
            System.out.println(parse);

            FileInputStream fileInputStream = new FileInputStream("E:test");
        } catch (ParseException | FileNotFoundException e) {
            System.out.println("时间解析异常");
            throw new RuntimeException(e);
        }
    }
}
