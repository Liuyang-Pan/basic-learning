package org.example.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * purpose:反射用法
 * Java反射机制：得到编译后的Class文件对象
 *
 * @author Pan Liuyang
 * 2022/11/15 21:20
 */
public class ReflectionUsageTest {

    /**
     * 获取类对象
     */
    @Test
    public void getClassMethod() {
        try {
            //1、通过全限名拿到Class类 源代码阶段
            Class<?> aClass = Class.forName("org.example.reflection.ReflectionTest");
            //2、定义阶段
            Class<ReflectionTest> reflectionTestClass = ReflectionTest.class;
            //3、实例化阶段
            ReflectionTest reflectionTest = new ReflectionTest();
            Class<? extends ReflectionTest> instantiationReflection = reflectionTest.getClass();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取构造器
     */
    @Test
    public void getConstructorMethod() {
        try {
            Class<?> aClass = Class.forName("org.example.reflection.ReflectionTest");
            /*
                public Constructor<?>[] getConstructors()获取public构造器对象数组
                public Constructor<T> getConstructor(Class<?>... parameterTypes)获取public单个构造器
             */
            {
                Constructor<?>[] constructors = aClass.getConstructors();
                System.out.println("--------------------------------------getConstructors()--------------------------------------");
                for (Constructor<?> constructor : constructors) {
                    System.out.println(constructor.getName() + "构造器有" + constructor.getParameterCount() + "个参数");
                }
            }
            {
                System.out.println("--------------------------------------getConstructor()--------------------------------------");
                //获取无参构造器
                Constructor<?> constructor = aClass.getConstructor();
                System.out.println(constructor.getName() + "构造器有" + constructor.getParameterCount() + "个参数");
            }
            /*
                public Constructor<?>[] getDeclaredConstructors() 返回所有的构造器对象数组
                public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) 返回单个造器对象数组
             */
            {
                Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
                System.out.println("--------------------------------------getDeclaredConstructors()--------------------------------------");
                for (Constructor<?> declaredConstructor : declaredConstructors) {
                    System.out.println(declaredConstructor.getName() + "构造器有" + declaredConstructor.getParameterCount() + "个参数");
                }
            }
            {
                System.out.println("--------------------------------------getDeclaredConstructor()--------------------------------------");
                //获取无参构造器
                Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                System.out.println(declaredConstructor.getName() + "构造器有" + declaredConstructor.getParameterCount() + "个参数");
                //获取参数是(String xxx)的构造器
                Constructor<?> declaredConstructorString = aClass.getDeclaredConstructor(String.class);
            }
            /*
                获取一个有参构造器创建一个对象
             */
            {
                System.out.println("--------------------------------------newInstance()--------------------------------------");
                //获取参数是(String xxx,String xxx)的构造器
                Constructor<?> declaredConstructorString = aClass.getDeclaredConstructor(String.class, String.class);
                //暴力反射 可将私有的权限打开，仅此次有效
                declaredConstructorString.setAccessible(true);
                //利用反射获取私有构造器创建对象
                ReflectionTest reflection = (ReflectionTest) declaredConstructorString.newInstance("反射创建的类", "私有构造器");
                System.out.println(reflection);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取成员变量
     */
    @Test
    public void getMemberVariablesMethod() {
        try {
            Class<?> aClass = Class.forName("org.example.reflection.ReflectionTest");
            /*
                public Field[] getDeclaredFields() 获取全部成员变量
                public Field getDeclaredField(String name) 成员变量名称匹配
             */
            {
                System.out.println("--------------------------------------getDeclaredFields()--------------------------------------");
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    System.out.println(aClass.getName() + "类的成员变量之一" + declaredField.getName() + "字段类型是" + declaredField.getType());
                }
                System.out.println("--------------------------------------getDeclaredFields()--------------------------------------");
                Field declaredField = aClass.getDeclaredField("name");
                System.out.println("获取到" + aClass.getName() + "类的成员变量之一" + declaredField.getName() + "字段类型是" + declaredField.getType());
            }
            /*
                public Field[] getFields() 获取权限是public的成员变量
                public Field getField(String name) 成员变量名称匹配
             */
            {
                System.out.println("--------------------------------------getFields()--------------------------------------");
                Field[] fields = aClass.getFields();
                for (Field field : fields) {
                    System.out.println(aClass.getName() + "类的成员变量之一" + field.getName() + "字段类型是" + field.getType());
                }
                Field field = aClass.getField("CONSTANT");
                System.out.println("获取到" + aClass.getName() + "类的成员变量之一" + field.getName() + "字段类型是" + field.getType());
            }
            /*
                成员变量赋值取值
             */
            {
                System.out.println("--------------------------------------set(Object obj, Object value)--------------------------------------");
                Field declaredField = aClass.getDeclaredField("name");
                //暴力反射
                declaredField.setAccessible(true);
                //public void set(Object obj, Object value) 成员变量赋值
                ReflectionTest reflectionTest = new ReflectionTest();
                //给成员变量赋值
                declaredField.set(reflectionTest, "反射成员变量赋值");
                System.out.println(reflectionTest);
                //获取成员变量的值
                String o = (String) declaredField.get(reflectionTest);
                System.out.println("获取到反射字段的值:" + o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取成员方法
     */
    @Test
    public void getMemberMethod() {
        try {
            Class<?> aClass = Class.forName("org.example.reflection.ReflectionTest");
            /*
                public Method[] getDeclaredMethods() 获取全部成员方法
                public Method getDeclaredMethod(String name, Class<?>... parameterTypes) 获取指定的成员方法(名称,多个参数类型匹配)
             */
            {
                System.out.println("--------------------------------------getDeclaredMethods()--------------------------------------");
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    System.out.println(aClass.getName() + "类的成员方法之一" + declaredMethod.getName() + "有" + declaredMethod.getParameterCount()
                            + "个参数,返回类型是" + declaredMethod.getReturnType());
                }
                Method declaredMethod = aClass.getDeclaredMethod("setName", String.class);
                System.out.println("获取到" + aClass.getName() + "类中成员方法" + declaredMethod.getName() + "有" + declaredMethod.getParameterCount()
                        + "个参数,返回类型是" + declaredMethod.getReturnType());

            }
            /*
                public Method[] getMethods() 获取权限为public的成员方法
                public Method getMethod(String name, Class<?>... parameterTypes) 获取权限为public的指定成员方法(名称,多个参数类型匹配)
             */
            {
                System.out.println("--------------------------------------getMethods()--------------------------------------");
                Method[] methods = aClass.getMethods();
                System.out.println("共获取到" + methods.length + "个成员方法如下:");
                for (Method method : methods) {
                    System.out.println(aClass.getName() + "类的public成员方法之一" + method.getName() + "有" + method.getParameterCount()
                            + "个参数,返回类型是" + method.getReturnType());
                }
                Method method = aClass.getMethod("getConstant");
                System.out.println("获取到" + aClass.getName() + "类的public成员方法" + method.getName() + "有" + method.getParameterCount()
                        + "个参数,返回类型是" + method.getReturnType());
            }
            /*
                调用获取到的成员方法
                运行方法
                Object invoke(Object obj，Object... args)
                参数一:用obj对象调用该方法
                参数二:调用方法的传递的参数(如果没有就不写)
                返回值:方法的返回值（如果没有就不写)

             */
            {
                System.out.println("--------------------------------------invoke()--------------------------------------");
                Method method = aClass.getMethod("getConstant");
                ReflectionTest reflectionTest = new ReflectionTest();
                Object invoke = method.invoke(reflectionTest);
                System.out.println(invoke);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 绕过编译阶段为集合添加数据 忽略泛型定义
     */
    @Test
    public void bypassBuildMethod() {
        //泛型在编译阶段时会限制添加的类型
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        Class<? extends List> stringListClass = stringList.getClass();
        Class<? extends List> integerListClass = integerList.getClass();
        //通过反射获取到类是相等的
        System.out.println(stringListClass == integerListClass);
        try {
            Method add = integerListClass.getDeclaredMethod("add", Object.class);
            boolean flag = (boolean) add.invoke(integerList, "放一个字符串");
            System.out.println(flag ? "成功放入一个String类型的数据：" + integerList : "执行失败");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
