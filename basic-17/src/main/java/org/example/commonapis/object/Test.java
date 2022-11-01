package org.example.commonapis.object;

public class Test {
    public static void main(String[] args) {
        ObjectCommon objectCommon = new ObjectCommon();
        ObjectCommon objectCommonEquals = new ObjectCommon();
        //Object自带的toString默认输出包路径.类名@对象地址,开发中一般会重写toString方法来输出对象的内容
        System.out.println(objectCommon.toString());
        //Object自带的equals方法也是默认对比对象地址,开发中一般会重写equals方法对比对象的内容
        System.out.println(objectCommon.equals(objectCommonEquals));
    }
}
