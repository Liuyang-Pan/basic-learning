package org.example.file.io;

import java.io.*;

/**
 * 字节缓存流 自带8KB缓存池
 * BufferedInputStream/BufferedOutputStream
 * 字符缓存流
 * BufferedReader/BufferedWriter
 */
public class BufferStream {
    public static void main(String[] args) {
//        bufferedInputOutputStreamMethod();
        bufferedReaderWriterStreamMethod();
    }

    public static void bufferedInputOutputStreamMethod() {
        try (
                //输入流
                BufferedInputStream bufferedInputStream = new BufferedInputStream(
                        new FileInputStream("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log")
                );
                //输出流
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                        new FileOutputStream("/IdeaProjects/basic-learning/basic/src/main/resources/write.txt")
                )
        ) {
            byte[] bytes = new byte[1024];
            int read;
            //此处的read是从内存的8KB缓存区读取出来的，更快
            while ((read = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, read);
                System.out.print(new String(bytes, 0, read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bufferedReaderWriterStreamMethod() {
        try (
                //输入流
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log")
                );
                //输出流
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter("/IdeaProjects/basic-learning/basic/src/main/resources/write.txt")
                )
        ) {
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                bufferedWriter.write(readLine);
                bufferedWriter.newLine();
                System.out.println(readLine);
                System.out.println("--------------------------");
            }
            //以上读取完毕之后就会关闭，以下代码不会被执行
            char[] bytes = new char[1024];
            int read;
            //此处的read是从内存的8KB缓存区读取出来的，更快
            while ((read = bufferedReader.read(bytes)) != -1) {
                bufferedWriter.write(bytes, 0, read);
                System.out.print(new String(bytes, 0, read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
