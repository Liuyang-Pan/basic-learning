package org.example.commonapis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ArraysCommon {
    public static void main(String[] args) {
        int[] intArray = {10, 20, 50, 40, 30};
        //输出数组
        System.out.println(Arrays.toString(intArray));
        //数组排序 默认升序
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));
        //二分搜索技术（前提是数据必须排好序）
        //返回索引，若未查找到会返回数组中排序好的插入后的一位并带上负号，例如：查找25会返回-3
        int i = Arrays.binarySearch(intArray, 25);
        System.out.println(i);
        if (i > 0) {
            System.out.println(intArray[i]);
        }

        System.out.println("------------------------Comparator------------------------");
        //Comparator比较器对象
        Integer[] comArray = {10, 30, 20, 40, 50, 55, 40, 40, 70, 99, 87};
        //Comparator仅支持引用类型
        Arrays.sort(comArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //升序
//                if (o1 > o2) {
//                    return 1;
//                } else if (o1 < o2) {
//                    return -1;
//                }
                //降序
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(comArray));
        System.out.println(Arrays.toString(selectSort(comArray)));
        System.out.println(binarySearch(comArray, 40));
    }

    /**
     * 选择排序：每轮排序时选择当前的位置与后面的进行对比，把较小的值交换到前面来
     *
     * @param integers 需要排序的数组
     * @return 返回排好序的数组
     */
    public static Integer[] selectSort(Integer[] integers) {
        for (int i = 0; i < integers.length - 1; i++) {
            for (int j = i + 1; j < integers.length; j++) {
                if (integers[i] > integers[j]) {
                    int temp = integers[i];
                    integers[i] = integers[j];
                    integers[j] = temp;
                }
            }
        }
        return integers;
    }

    /**
     * 二分查找算法
     *
     * @param integers 需要查询的数组
     * @param data     需要检索的数据
     * @return 返回检索到的索引
     */
    public static Integer binarySearch(Integer[] integers, Integer data) {
        if (Objects.isNull(integers) || integers.length == 0) {
            return -1;
        }
        //1、定义左边位置
        int min = 0;
        //2、定义右边位置
        int max = integers.length - 1;
        //3、循环对折查询
        while (min <= max) {
            int middleIndex = (min + max) / 2;
            //判断中间索引位置与要找的元素的大小情况
            if (data > integers[middleIndex]) {
                min = middleIndex + 1;
            } else if (data < integers[middleIndex]) {
                max = middleIndex - 1;
            } else {
                return middleIndex;
            }
        }
        return -1;
    }
}
