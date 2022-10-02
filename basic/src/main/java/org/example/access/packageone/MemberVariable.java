package org.example.access.packageone;

import org.junit.jupiter.api.Test;

/**
 * purpose:访问控制权限类
 *
 * @author Pan Liuyang
 * 2022/9/28 17:03
 */
public class MemberVariable {
    /**
     * 默认访问控制权限
     * 该成员可以被该类内部成员访问，也可以被同一包下其他的类访问；
     */
    String stringDefault = "Default Access Control Permissions!";

    /**
     * 私有访问控制权限
     * 该成员可以被该类内部成员访问；
     */
    private String stringPrivate = "Private Access Control Permissions!";

    /**
     * 受保护的访问控制权限
     * 该成员可以被该类内部成员访问，也可以被同一包下其他的类访问，还可以被它的子类访问；
     */
    protected String stringProtected = "Protected Access Control Permissions!";

    /**
     * 公共的的访问控制权限
     * 该成员可以被任意包下，任意类的成员进行访问。
     */
    public String stringPublic = "Public Access Control Permissions!";

    @Test
    public void AccessTest() {
        System.out.println("Print:" + stringDefault);
        System.out.println("Print:" + stringPrivate);
        System.out.println("Print:" + stringProtected);
        System.out.println("Print:" + stringPublic);
    }
}
