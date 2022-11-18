package org.example.file.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * purpose:Commons-IO框架
 *
 * @author Pan Liuyang
 * 2022/11/12 10:51
 */
public class CommonsIOUsage {
    public static void main(String[] args) {
        try (
                FileInputStream fileInputStream = new FileInputStream("basic/src/main/resources/logback.xml");
                FileOutputStream fileOutputStream = new FileOutputStream("basic/src/main/resources/copy/logback.xml")
        ) {
            //文件复制
            IOUtils.copy(fileInputStream, fileOutputStream);
            //文件复制到文件夹
            FileUtils.copyFileToDirectory(new File("basic/src/main/resources/logback.xml"), new File("basic/src/main/resources/fileCopy"));
            //文件夹复制到文件夹
            FileUtils.copyDirectoryToDirectory(new File("basic/src/main/resources"), new File("basic/src/main/resources/directory"));
            //NIO
//            Files.copy(Path.of("E:\\IDEAProject\\basic-learning\\basic\\src\\main\\resources/logback.xml"), Path.of("E:\\IDEAProject\\basic-learning\\basic\\src\\main\\resources\\copy/logback.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
