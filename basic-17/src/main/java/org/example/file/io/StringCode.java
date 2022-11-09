package org.example.file.io;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 字符串编码解码
 */
public class StringCode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //编码:文字转换为字节
        String string = "string字符串";
        System.out.println("-----------------当前代码默认字符集编码获取-----------------");
        byte[] bytes = string.getBytes();
        System.out.println(Arrays.toString(bytes));
        System.out.println("-----------------GBK编码获取-----------------");
        byte[] gbkString = string.getBytes("GBK");
        System.out.println(Arrays.toString(gbkString));

        //解码
        String s = new String(bytes);
        System.out.println(s);
        String gbk = new String(gbkString, "GBK");
        System.out.println(gbk);
    }
}
