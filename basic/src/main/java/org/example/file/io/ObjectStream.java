package org.example.file.io;

import org.example.file.io.entity.SerializableUsage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化:将某一个Java对象格式持久化到磁盘中
 * 反序列化:将磁盘中的内容转换成Java对象读取
 */
public class ObjectStream {
    public static void main(String[] args) {
        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(
                        new FileOutputStream("/IdeaProjects/basic-learning/basic/src/main/resources/ObjectOutputStream.txt")
                );

                ObjectInputStream inputStream = new ObjectInputStream(
                        new FileInputStream("/IdeaProjects/basic-learning/basic/src/main/resources/ObjectOutputStream.txt")
                )
        ) {
            //以Java的Serial(序列化)方式存入到文件中
            outputStream.writeObject(new SerializableUsage(10, 10.10, "序列化"));
            //反序列化读取出来
            System.out.println(inputStream.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
