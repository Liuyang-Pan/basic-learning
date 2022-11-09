package org.example.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 递归
 */
public class RecursionUsage {

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    /**
     * 递归
     *
     * @param n 多少的阶乘
     * @return 返回每次计算的结果
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
}

class RecursionFileUsage {
    public static void main(String[] args) {
        searchFile(new File("D:"), "basic.log");
    }

    public static void searchFile(File dir, String fileName) {
        if (null == dir || !dir.isDirectory() || null == fileName) {
            System.out.println("空值或文件路径不是文件夹");
            return;
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (fileName.contains(file.getName()))
                        System.out.println("找到了,文件路径是:" + file.getAbsoluteFile());
                } else {
                    searchFile(file, fileName);
                }
            }
        }
    }
}