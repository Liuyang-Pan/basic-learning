package org.example.collection.collcommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

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
