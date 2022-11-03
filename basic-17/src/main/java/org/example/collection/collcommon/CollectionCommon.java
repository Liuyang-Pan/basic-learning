package org.example.collection.collcommon;

import java.util.*;

public class CollectionCommon {
    public static void main(String[] args) {
        collection();
    }

    public static void collection() {
        //List 有序、可重复、有索引
        Collection<String> listCollection = new ArrayList<>();
        listCollection.add("List");
        listCollection.add("ArrayList");
        //Set 无序、不可重复、无索引
        Collection<String> setCollection = new HashSet<>();

        //遍历method1:迭代器-得到集合的第一个索引0
        Iterator<String> iterator = listCollection.iterator();
        System.out.println("------------------------------迭代器遍历------------------------------");
        //hasNext()判断是否存在元素
        while (iterator.hasNext()) {
            //next()取出元素并将索引移到下一个
            System.out.println(iterator.next());
        }
        System.out.println("------------------------------forEach遍历------------------------------");
        //遍历method2:增强for循环-
        for (String list : listCollection) {
            System.out.println(list);
        }
        //也可以遍历数组
        int[] arr = {10, 20};
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("------------------------------Lambda-forEach遍历------------------------------");
        listCollection.forEach(System.out::println);
    }
}

/**
 * List 有序、可重复、有索引
 * ArrayList底层是基于数组实现的，根据查询元素快，增删相对慢。
 * 1、根据索引定位元素快，增删需要做元素的移位操作。
 * 2、第一次创建集合并添加第一个元素的时候，在底层创建一个默认长度为10的数组。
 * LinkedList底层基于双链表实现的，查询元素慢，增删首尾元素是非常快的.
 */
class ListCommon {
}

/**
 * Set 无序、不可重复、无索引
 * 无序:不一定按照存入的顺序取
 * 不重复:去重
 * 无重复:没有带索引的方法
 * 实现类:
 * HashSet:无序、不重复、无索引。
 * 底层采用哈希表存储数据,哈希表对于增删改查性能较好
 * 哈希表组成:
 * JDK8前是数组+链表-
 * JDK8之后是数组+链表+红黑树
 * LinkedHashSet:有序、不重复、无索引。
 * TreeSet:排序、不重复、无索引。
 */
class SetCommon {
    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("Java");
        stringSet.add("Spring");
        stringSet.add("Set");
        stringSet.add("Spring");
        System.out.println(stringSet);
    }
}