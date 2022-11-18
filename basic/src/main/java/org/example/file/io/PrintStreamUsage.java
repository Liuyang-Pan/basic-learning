package org.example.file.io;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 字节流
 * PrintStream
 * 字符流
 * PrintWriter
 */
public class PrintStreamUsage {
    public static void main(String[] args) {
        try (
                PrintStream printStream = new PrintStream(new FileOutputStream("/IdeaProjects/basic-learning/basic-17/src/main/resources/PrintStream.txt", true));

                PrintWriter printWriter = new PrintWriter(new FileOutputStream("/IdeaProjects/basic-learning/basic-17/src/main/resources/PrintStream.txt", true));
        ) {
            //系统的打印流改成文件流打印到文件中
            System.setOut(printStream);
            System.out.println("测试");
            printStream.println("字节流输入什么就打印什么到文件中");
            printWriter.println("字符流输入什么就打印什么到文件中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
