package org.example.genericity;

import java.util.List;

/**
 * 泛型类
 */
public class GenericUsage<T> {

    /**
     * @param t   形参
     * @param e   形参
     * @param <T> 限制T入参
     * @param <E> 限制E入参
     */
    private static <T, E> T method(T t, E e) {
        return t;
    }

    /**
     * ? extends GenericUsage传入类型上限为GenericUsage,仅能传入GenericUsage及其子类
     *
     * @param list 上限参数
     */
    private void wildcardExtends(List<? extends GenericUsage> list) {

    }

    /**
     * ? super GenericUsage传入类型下线为GenericUsage,仅能传入GenericUsage及其父类
     *
     * @param list 下限参数
     */
    private void wildcardSupper(List<? super GenericUsage> list) {

    }
}

/**
 * 泛型接口
 *
 * @param <T>
 */
interface GenericInterface<T> {
}