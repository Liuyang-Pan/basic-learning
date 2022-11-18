package org.example.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Slf4j
public class FileUsage {
    public static void main(String[] args) throws IOException {
        log.info("开始读取文件...");
        //可以是绝对路径也可以是相对路径，相对路径默认路径是当前工程路径，也可以获取文件夹
        File file = new File("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log");
        //返回字节(byte)数
        long length = file.length();
        log.info("文件大小:" + length + "B");
        log.info("当前读取到的是:" + (file.isDirectory() ? "文件夹" : "文件"));
        log.info("当前读取到的是:" + (file.isFile() ? "文件" : "文件夹"));
        log.info("当前文件的绝对路径是:" + file.getAbsoluteFile());
        log.info("pathname定义的路径是:" + file.getPath());
        log.info("文件最后修改时间:" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(file.lastModified()));
        log.info("文件名称:" + file.getName());
        log.info("文件或文件夹" + (file.exists() ? "存在" : "不存在"));


        //public boolean createNewFile() 创建一个新的空的文件
        File createFile = new File("/IdeaProjects/basic-learning/basic/src/main/resources/basicFile.txt");
        boolean newFile = createFile.createNewFile();
        log.info(newFile ? "文件创建成功" : "文件可能已经存在，创建失败");
        //public boolean mkdir() 只能创建一级文件夹
        File mkdirFile = new File("/IdeaProjects/basic-learning/basic/src/main/resources/mkdirFile");
        log.info(mkdirFile.mkdir() ? "文件夹创建成功" : "文件夹创建失败");
        //public boolean mkdirs() 可以创建多级文件夹
        File mkdirsFile = new File("/IdeaProjects/basic-learning/basic/src/main/resources/mkdirsFile/mkdir/file");
        log.info(mkdirsFile.mkdirs() ? "文件夹创建成功" : "文件夹创建失败");
        //public boolean delete() 删除由此抽象路径名表示的文件或空文件夹
        //delete方法直接删除不走回收站,delete方法默认只能删除空文件夹。
        log.info(createFile.delete() ? "创建的文件删除成功" : "创建的文件删除失败");
        log.info(mkdirFile.delete() ? "创建的文件夹删除成功" : "创建的文件夹删除失败");

        //无文件列表会返回空数组 若是路径是文件会返回空对象null
        File fileList = new File("/IdeaProjects/basic-learning/basic/src/main/resources");
        log.info("当前文件夹的文件和文件夹列表:" + Arrays.toString(fileList.list()));
        log.info("当前文件夹的文件和文件夹File列表" + Arrays.toString(fileList.listFiles()));
    }
}
