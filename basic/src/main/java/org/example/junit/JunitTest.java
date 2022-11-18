package org.example.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * purpose:JunitTest
 *
 * @author Pan Liuyang
 * 2022/11/15 21:00
 * @Test 测试方法
 * @BeforeEach 用来修饰实例方法，该方法会在每一个测试方法执行之前执行一次。
 * @AfterEach 用来修饰实例方法，该方法会在每一个测试方法执行之后执行一次。
 * @BeforeAll 用来静态修饰方法，该方法会在所有测试方法之前只执行一次。
 * @AfterAll 用来静态修饰方法，该方法会在所有测试方法之后只执行一次。
 */
public class JunitTest {

    @Test
    public void test() {
        BusinessTest businessTest = new BusinessTest();
        //expected期待值,实际传入值，若实际传入值!=期待值则提示后面的message
        Assertions.assertEquals("String", businessTest.returnString(), "业务可能存在问题");
        businessTest.printHelloWorld();
    }

    @Test
    public void errTest() {
        BusinessTest businessTest = new BusinessTest();
        businessTest.errorMethod();
    }
}
