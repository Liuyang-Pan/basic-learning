package org.example.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * purpose:注解用于标记类、方法、字段等信息，用于特殊处理
 *
 * @author Pan Liuyang
 * 2022/11/16 14:46
 */
@CustomizeAnnotation(name = "名称", names = {"multipleName"})
public class AnnotationTest {

    /**
     * 解析注解
     */
    @Test
    public void test() {
        //反射的类方法等类都实现了AnnotatedElement接口，可以解析注解的内容
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
        //判断这个类是否存在CustomizeAnnotation注解
        if (annotationTestClass.isAnnotationPresent(CustomizeAnnotation.class)) {
            //获取类上的CustomizeAnnotation注解
            CustomizeAnnotation declaredAnnotation = annotationTestClass.getDeclaredAnnotation(CustomizeAnnotation.class);
            System.out.println("|defaultValue:" + declaredAnnotation.defaultValue() + "\n|name:" + declaredAnnotation.name()
                    + "\n|names:" + Arrays.toString(declaredAnnotation.names()));
        }

        try {
            Method defaultAnnotation = annotationTestClass.getMethod("defaultAnnotation");
            if (defaultAnnotation.isAnnotationPresent(DefaultAnnotation.class)) {
                DefaultAnnotation declaredAnnotation = defaultAnnotation.getDeclaredAnnotation(DefaultAnnotation.class);
                System.out.println("declaredAnnotation.value() = " + declaredAnnotation.value());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @DefaultAnnotation("value")
    public void defaultAnnotation() {

    }
}
