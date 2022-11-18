package org.example.commonapis;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalCommon {
    public static void main(String[] args) {
        //浮点型运算的时候直接+*/可能会出现数据失真（精度问题）。
        System.out.println(0.09 + 0.01);
        System.out.println(1.0 - 0.32);
        System.out.println(1.015 * 100);
        System.out.println(1.301 / 100);
        System.out.println("----------------------------");
        double a = 0.1;
        double b = 0.2;
        double c = a + b;
        System.out.println(c);
        System.out.println("-------------BigDecimal---------------");
        BigDecimal bigDecimalA = BigDecimal.valueOf(a);
        BigDecimal bigDecimalB = BigDecimal.valueOf(b);
        //+
        System.out.println(bigDecimalA.add(bigDecimalB));
        //-
        System.out.println(bigDecimalB.subtract(bigDecimalA));
        //*
        System.out.println(bigDecimalB.multiply(bigDecimalA));
        //除法
        System.out.println(bigDecimalB.divide(bigDecimalA));
        //转换为double类型输出
        System.out.println(bigDecimalA.doubleValue());
        //BigDecimal必须精度运算
        BigDecimal bigDecimalC = BigDecimal.valueOf(10);
        BigDecimal bigDecimalD = BigDecimal.valueOf(3);
        //直接调用divide方法若除不尽时会抛出ArithmeticException异常，使用divide的四舍五入等方式可以避免
        System.out.println(bigDecimalC.divide(bigDecimalD, 2, RoundingMode.HALF_UP));
    }
}
