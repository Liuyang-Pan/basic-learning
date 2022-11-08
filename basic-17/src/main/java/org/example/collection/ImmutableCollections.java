package org.example.collection;

import java.util.*;

/**
 * 不可变集合
 */
public class ImmutableCollections {
    public static void main(String[] args) {
        //JDK9以上才有的方法
        List<Double> stringList = List.of(1.2, 12.2, 12.11, 12.1111, 12121.1);
        //更改会抛出java.lang.UnsupportedOperationException异常
//        stringList.add(null);
        System.out.println(stringList);
        Set<Double> doubles = Set.of(1.2, 12.2, 12.11, 12.1111, 12121.1);
        //更改会抛出java.lang.UnsupportedOperationException异常
//        doubles.add(null);
        System.out.println(doubles);
        Map<String, Integer> test = Map.of("Test", 1, "Test2", 2);
        System.out.println(test);
        //转换为不可变的
        List<Double> ts = Collections.unmodifiableList(new ArrayList<>());
    }
}
