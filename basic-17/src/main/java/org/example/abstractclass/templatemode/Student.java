package org.example.abstractclass.templatemode;

/**
 * 学生类 抽象类
 * 采用模板方法模式设计
 */
public abstract class Student {

    /**
     * final修饰，无法重写，模板不会被重写
     */
    public final void write() {
        System.out.println("《标题》");
        System.out.println("第一段");
        writeText();
        System.out.println("第二段");
    }

    public abstract void writeText();
}
