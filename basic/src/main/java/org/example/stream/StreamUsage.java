package org.example.stream;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Steam流的使用
 */
public class StreamUsage {
    public static void main(String[] args) {
        //---------------------------------------Collection获取流---------------------------------------
        Collection<String> strings = new ArrayList<>();
        Stream<String> stream = strings.stream();
        //---------------------------------------Map获取流---------------------------------------
        Map<Integer, String> map = new HashMap<>();
        Stream<Integer> stream1 = map.keySet().stream();
        Stream<String> stream2 = map.values().stream();
        Stream<Map.Entry<Integer, String>> stream3 = map.entrySet().stream();
        //---------------------------------------数组获取流---------------------------------------
        String[] strings1 = {"Collection", "Map", "Array"};
        Stream<String> stream4 = Arrays.stream(strings1);

        Stream<String> strings11 = Stream.of(strings1);
    }
}

class StreamCommon {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Stream");
        list.add("filter");
        list.add("limit");
        list.add("skip");
        list.add("distinct");
        list.add("concat");
        list.add("distinct");
        list.add("distinct ");
        //stream<T> filter(Predicate<? super T> predicate)用于对流中的数据进行过滤。
        System.out.println("---------------------------filter---------------------------");
        list.stream().filter(s -> s.startsWith("s") || s.startsWith("S")).forEach(System.out::println);
        //Stream<T> limit(long maxSize)获取前几个元素
        System.out.println("---------------------------limit---------------------------");
        list.stream().limit(2).forEach(System.out::println);
        //Stream<T> skip(long n)跳过前几个元素
        System.out.println("---------------------------skip---------------------------");
        list.stream().skip(2).forEach(System.out::println);
        //stream<T> distinct() 去除流中重复的元素。依赖(hashCode和equals方法)
        System.out.println("---------------------------distinct---------------------------");
        list.stream().distinct().forEach(System.out::println);
        //<R> Stream<R> map(Function<? super T, ? extends R> mapper); 加工方法
        System.out.println("---------------------------map---------------------------");
        list.stream().map(s -> "Stream:" + s).forEach(System.out::println);
        //static <T> Stream<T> concat(Stream a，Stream b)合并a和b两个流为一个流
        Stream<Integer> integerStream = list.stream().map(String::hashCode);
        Stream<Integer> stream = Stream.of(11, 111, 1111);
        Stream<Integer> concat = Stream.concat(integerStream, stream);
        concat.forEach(System.out::println);
        //collect收集流
        System.out.println("---------------------------collect---------------------------");
        List<String> collect = list.stream().map(String::strip).collect(Collectors.toList());
        Set<String> collect1 = list.stream().map(String::strip).collect(Collectors.toSet());
        Object[] objects = list.toArray();
        System.out.println(collect);
        System.out.println(collect1);
        System.out.println(Arrays.toString(objects));
        String[] strings = list.toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
    }
}