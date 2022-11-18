package org.example.packaging;

/**
 * 自动装箱:基本类型的数据和变量可以直接赋值给包装类型的变量。
 * 自动拆箱:包装类型的变量可以直接赋值给基本数据类型的变量。
 */
public class Packaging {
    public static void main(String[] args) {
        int a = 10;
        //自动装箱
        Integer aa = a;

        Integer b = 10;
        //自动拆箱
        int bb = b;
    }
}
