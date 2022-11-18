package org.example.abstractclass.templatemode;

/**
 * 小学生
 */
public class Pupil extends Student {
    @Override
    public void writeText() {
        System.out.println("小学生写的正文内容");
    }
}
