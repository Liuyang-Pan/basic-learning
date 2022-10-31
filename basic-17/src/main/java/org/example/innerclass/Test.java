package org.example.innerclass;

public class Test {
    public static void main(String[] args) {
        InnerClass.StaticInnerClass staticInnerClass = new InnerClass.StaticInnerClass();
        InnerClass.MemberInnerClass memberInnerClass = new InnerClass().new MemberInnerClass();
    }
}
