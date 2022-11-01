package org.example.commonapis.stringbuilder;

/**
 * StringBuilder性能高于String
 * String的+号拼接本质上也会创建一个StringBuilder对象把拼接的内容放进去再转换成String，每次会开辟一个新的堆内存，再丢掉之前的内存。
 */
public class StringBuilderTest {

    public static void main(String[] args) {
        StringBuilder stringBuilderOne = new StringBuilder();
        stringBuilderOne.append("a");
        stringBuilderOne.append(1);
        stringBuilderOne.append(true);
        //StringBuilder重写了toString方法会输出内容
        System.out.println(stringBuilderOne);

        StringBuilder stringBuilderTwo = new StringBuilder("StringBuilderTwo");
        stringBuilderTwo.append(1).append(2);
        //字符串反转
        stringBuilderTwo.reverse();
        System.out.println(stringBuilderTwo.toString());
        System.out.println(stringBuilderTwo.length());
    }
}
