package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 元注解:</br>
 * 1、@Target:约束自定义注解只能在哪些地方使用 |TYPE类、接口|FIELD成员变量|METHOD成员方法|PARAMETER方法参数|CONSTRUCTOR构造器|LOCAL_VARIABLE局部变量|
 * 2、@Retention:由明注解的生命周期 SOURCE:注解只作用在源码阶段，生成的字节码文件中不存在|CLASS:注解作用在源码阶段，字节码文件阶段，运行阶段不存在，默认值
 * |RUNTIME︰注解作用在源码阶段，字节码文件阶段，运行阶段（开发常用）
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomizeAnnotation {
    String name();

    String[] names();

    //存在默认值调用时可以不用传入此参数
    String defaultValue() default "defaultValue";
}

/**
 * 仅一个value()属性在调用注解是可省略value=
 */
@Retention(RetentionPolicy.RUNTIME)
@interface DefaultAnnotation {
    String value();
}