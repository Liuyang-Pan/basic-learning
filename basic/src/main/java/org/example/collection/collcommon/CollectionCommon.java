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
 * 哈希值:JDK根据对象的地址按照哈希算法计算出的int值,可以调用对象的hashCode()方法获取哈希值
 * JDK8前是数组+链表-
 * 1、创建一个默认长度16的数组，数组名table，当数组长度存到16*0.75=12时开始翻倍扩容
 * 2、根据元素的哈希值跟数组的长度求余计算出应存入的位置（哈希算法)
 * 3、判断当前位置是否为null，如果是null直接存入
 * 4、如果位置不为null，表示有元素，则调用equals方法比较。如果一样，则不存，如果不一样，则存入数组
 * 5、JDK7新元素占老元素位置，指向老元素；JDK8中新元素挂在老元素下面
 * JDK8之后是数组+链表+红黑树-
 * 1、当挂在元素下面的数据过多时，查询性能降低，从JDK8开始后，当链表长度超过8的时候，自动转换为红黑树。
 * LinkedHashSet:有序、不重复、无索引。
 * 双链表方式存储保证有序
 * TreeSet:排序、不重复、无索引。
 * 可排序基于红黑树方式进行排序，并且TreeSet必须排序，数字类型默认采用升序，字符类型默认以首字母编号顺序升序
 */
class SetCommon {
    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("Java");
        stringSet.add("Spring");
        stringSet.add("Set");
        stringSet.add("Spring");
        System.out.println(stringSet);

        Set<Integer> integerSet = new TreeSet<>();
        integerSet.add(19);
        integerSet.add(20);
        integerSet.add(18);
        integerSet.add(17);
        integerSet.add(21);
        System.out.println(integerSet);

        /*
            TreeSet必须制定排序规则
            1、自定义类实现Comparable接口重写compareTo方法
            2、TreeSet集合有参数构造器，可以设置comparator接口对应的比较器对象，来定制比较规则。
            集合定义了排序规则优先按照集合定义的排序规则排序，排序规则返回0的时候会去重
        */
        Set<SetEntity> setEntities = new TreeSet<>((o1, o2) -> o1.getSetInt() - o2.getSetInt() >= 0 ? 1 : -1);
        SetEntity set = new SetEntity(10, "Set");
        SetEntity treeSet = new SetEntity(11, "TreeSet");
        SetEntity treeSetBig = new SetEntity(10, "TreeSet");
        setEntities.add(set);
        setEntities.add(treeSet);
        setEntities.add(treeSetBig);
        System.out.println(setEntities);
    }
}

class SetEntity implements Comparable<SetEntity> {
    private Integer setInt;
    private String setStr;

    @Override
    public int compareTo(SetEntity o) {
        return this.setInt - o.getSetInt() >= 0 ? 1 : -1;
    }

    public SetEntity(Integer setInt, String setStr) {
        this.setInt = setInt;
        this.setStr = setStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetEntity setEntity = (SetEntity) o;
        return Objects.equals(setInt, setEntity.setInt) && Objects.equals(setStr, setEntity.setStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setInt, setStr);
    }

    @Override
    public String toString() {
        return "SetEntity{" +
                "setInt=" + setInt +
                ", setStr='" + setStr + '\'' +
                '}';
    }

    public Integer getSetInt() {
        return setInt;
    }

    public void setSetInt(Integer setInt) {
        this.setInt = setInt;
    }

    public String getSetStr() {
        return setStr;
    }

    public void setSetStr(String setStr) {
        this.setStr = setStr;
    }
}

class VariableParameter {
    public static void main(String[] args) {
        variable();
        variable("1");
        variable("1", "2");
        variable(new String[]{"1", "2", "3"});
    }

    /**
     * 可变参数
     *
     * @param strings 一个方法仅能定义一个
     */
    public static void variable(String... strings) {
        //可变参数内部相当于一个数组
        System.out.println(Arrays.toString(strings));
    }
}