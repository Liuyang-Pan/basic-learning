package org.example.innerclass;

public class InnerClass {

    /**
     * 静态内部类
     */
    public static class StaticInnerClass {

    }

    /**
     * 成员内部类
     */
    public class MemberInnerClass {
        //JDK16之前不支持static静态
        public static String name;
    }

    /**
     * 局部内部类
     */
    public void localClass() {
        class LocalInnerClass {
            //JDK16之前不支持static静态
            public static String name;
        }

        LocalInnerClass localInnerClass = new LocalInnerClass();
    }
}
