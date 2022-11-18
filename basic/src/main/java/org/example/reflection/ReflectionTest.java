package org.example.reflection;

import org.example.annotation.CustomizeAnnotation;

/**
 * purpose:反射测试类
 *
 * @author Pan Liuyang
 * 2022/11/15 21:23
 */
public class ReflectionTest {
    private String name;

    private String privateString;

    public static final String CONSTANT = "常量";

    public ReflectionTest() {
    }

    public ReflectionTest(String name) {
        this.name = name;
    }

    private ReflectionTest(String name, String privateString) {
        this.name = name;
        this.privateString = privateString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReflectionTest{" +
                "name='" + name + '\'' +
                ", privateString='" + privateString + '\'' +
                '}';
    }

    public String getConstant() {
        return CONSTANT;
    }
}
