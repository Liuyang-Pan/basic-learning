package org.example.collection.map;

import java.util.*;

/**
 * Map特点:
 * Map集合的特点都是由键决定的。
 * 1、Map集合的键是无序,不重复的，无索引的，值不做要求（可以重复)。
 * 2、Map集合后面重复的键对应的值会覆盖前面重复键的值。
 * 3、Map集合的键值对都可以为null。
 */
public class MapCommon {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Xiaomi", 100);
        map.put("HUAWEI", 20);
        System.out.println("--------------------------键找值遍历--------------------------");
        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println("[key=" + string + ",value=" + map.get(string) + "]");
        }
        System.out.println("--------------------------键值对遍历--------------------------");
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("[key=" + entry.getKey() + ",value=" + entry.getValue() + "]");
        }
        System.out.println("--------------------------Lambda遍历--------------------------");
        map.forEach((key, value) -> System.out.println("[key=" + key + ",value=" + value + "]"));
    }
}

/**
 * HashMap:元素按照键是无序，不重复，无索引，值不做要求。(与Map体系一致)
 * HashMap跟HashSet底层原理是一模一样的，都是哈希表结构，只是HashMap的每个元素包含两个值而已。
 * 实际上:Set系列集合的底层就是Map实现的，只是Set集合中的元素只要键数据不要值数据而已。
 */
class HashMapCommon {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Xiaomi", 100);
        map.put("HUAWEI", 20);
        map.put("Apple", 100);
        map.put(null, null);
        System.out.println(map);
    }
}

/**
 * LinkedHashMap:元素按照键是有序，不重复，无索引，值不做要求。
 * 原理︰底层数据结构是依然哈希表，只是每个键值对元素又额外的多了一个双链表的机制记录存储的顺序。
 */
class LinkedHashMapCommon {
    public static void main(String[] args) {
        Map<String, Integer> map = new java.util.LinkedHashMap<>();
        map.put("Apple", 1);
        map.put("Xiaomi", 100);
        map.put("HUAWEI", 20);
        map.put("Apple", 100);
        map.put(null, null);
        System.out.println(map);
    }
}

/**
 * TreeMap:元素按照建是排序，不重复，无索引的，值不做要求。
 * 可排序:按照键数据的大小默认升序（有小到大）排序。只能对键排序。
 * 注意:TreeMap集合是一定要排序的，可以默认排序，也可以将键按照指定的规则进行排序
 * 自定义排序可参考TreeSet在键对象中实现Comparator接口
 */
class TreeMapCommon {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>((o1, o2) -> o2 - o1);
        map.put(1, "Apple");
        map.put(2, "Xiaomi");
        map.put(4, "HUAWEI");
        map.put(3, "OPPO");
        System.out.println(map);
    }
}
