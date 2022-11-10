package org.example.file.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 字符转换流
 * InputStreamReader/OutputStreamReader
 */
public class TransformationStream {
    public static void main(String[] args) {
        method();
    }

    public static void method() {
        try (
                //读取的文件转换成对应的编码
                InputStreamReader inputStreamReader = new InputStreamReader(
                        new FileInputStream("/IdeaProjects/basic-learning/basic-17/src/main/resources/basic.log"), "UTF-8"
                );
                //以某种字符流输出
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        new FileOutputStream("/IdeaProjects/basic-learning/basic-17/src/main/resources/write.txt"), "UTF-8"
                )
        ) {
            int len;
            while ((len = inputStreamReader.read()) != -1) {
                System.out.print(((char) len));
                outputStreamWriter.write(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
