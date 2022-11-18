package org.example.collection.collcommon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 集合工具类常用操作
 * public static <T> boolean addAll(Collection<? super T> c，T... elements):给集合对象批量添加元素!
 * public static void shuffle(List<?> list) :打乱集合顺序。
 * public static <T> void sort(List<T> list):将集合中元素按照默认规则排序。
 * public static <T> void sort(List<T> list，Comparator<? super T> c):将集合中元素按照指定规则排序。
 */
public class CollectionsCommon {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "String", "StringList", "StringArrayList");
        System.out.println("------------------------------addAll------------------------------");
        System.out.println(strings);
        System.out.println("------------------------------shuffle------------------------------");
        Collections.shuffle(strings);
        System.out.println(strings);
        System.out.println("------------------------------sort------------------------------");
        Collections.sort(strings);
        System.out.println(strings);
        System.out.println("------------------------------sortComparator------------------------------");
        Collections.sort(strings, (Comparator.comparingInt(String::hashCode)));
        System.out.println(strings);
    }
}
