package org.example.commonapis;

public class MathCommon {
    public static void main(String[] args) {
        //获取参数绝对值 int abs(int a)
        System.out.println(Math.abs(10)); // 10
        System.out.println(Math.abs(-10.3));// 10.3

        //向上取整 double ceil(double a)
        System.out.println(Math.ceil(4.00000001));// 5.0
        System.out.println(Math.ceil(4.0));// 4.0

        //向下取整 double floor( double a)
        System.out.println(Math.floor(4.99999999));//4.0
        System.out.println(Math.floor(4.0));// 4.0

        //四舍五入 int round(float a)
        System.out.println(Math.round(4.49999));// 4
        System.out.println(Math.round(4.500001)); // 5

        //获取两个int值中的较大值 int max(int a,int b)
        System.out.println(Math.max(1, 2));//2
        //返回a的b次幂的值 double pow(double a,double b)
        System.out.println(Math.pow(2, 3));// 2^3 = 8.0
        //返回double的随机数,范围:[0.0,1.0) double random()
        System.out.println(Math.random());
    }
}
