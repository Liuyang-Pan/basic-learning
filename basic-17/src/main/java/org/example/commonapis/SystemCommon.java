package org.example.commonapis;

import java.util.Arrays;

public class SystemCommon {
    public static void main(String[] args) {
        //返回当前系统的时间毫秒值形式 long currentTimeMillis()
        //从1970-01-01 00:00:00开始
        System.out.println(System.currentTimeMillis());
        //数组拷贝 void arraycopy (数据源数组, 起始索引, 目的地数组, 起始索引, 拷贝个数)
        //void arraycopy(Object src,int srcPos,Object dest,int destPos,int length);
        int[] arrOne = {10, 20, 30};
        int[] arrTwo = new int[3];
        System.arraycopy(arrOne, 0, arrTwo, 0, 3);
        System.out.println(Arrays.toString(arrTwo));
        //终止当前运行的Java虚拟机，非零表示异常终止 void exit(int status)
        System.exit(0);
    }
}
