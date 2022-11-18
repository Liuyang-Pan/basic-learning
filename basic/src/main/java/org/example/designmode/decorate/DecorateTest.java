package org.example.designmode.decorate;

/**
 * purpose:装饰模式:在不改变原有的类的情况下增强功能
 * 装饰设计模式的作用:装饰模式指的是在不改变原类的基础上，动态地扩展一个类的功能。
 * 主要用于在IO流时高级流装饰低级流
 * 1、定义父类。
 * 2、定义原始类，继承父类，定义功能。
 * 3、定义装饰类，继承父类，包装原始类，增强功能!!
 *
 * @author Pan Liuyang
 * 2022/11/17 16:58
 */
public class DecorateTest {
    public static void main(String[] args) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream());
        bufferedInputStream.read();
    }
}

/**
 * 1、定义共同父类
 */
abstract class InputStream {
    public abstract int read();

    public abstract int read(byte[] buffer);
}

/**
 * 原始类
 */
class FileInputStream extends InputStream {

    @Override
    public int read() {
        System.out.println("读取了一个文件字节");
        return 0;
    }

    @Override
    public int read(byte[] buffer) {
        System.out.println("读取了" + buffer.length + "个文件字节");
        return 0;
    }
}

/**
 * 装饰类 做功能增强
 */
class BufferedInputStream extends InputStream {
    private InputStream inputStream;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() {
        System.out.println("读取了一个文件字节并放入了缓存");
        return inputStream.read();
    }

    @Override
    public int read(byte[] buffer) {
        System.out.println("从缓存读取");
        return inputStream.read(buffer);
    }
}