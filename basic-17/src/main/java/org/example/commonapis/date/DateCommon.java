package org.example.commonapis.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCommon {
    public static void main(String[] args) throws ParseException {
        //构造器，获取当前系统时间
        Date date = new Date();
        System.out.println(date);
        //获取时间毫秒值
        System.out.println(date.getTime());

        Date simpleDate = new Date();
        //2022年11月01日 11:19:11 周二 上午
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE a");
        System.out.println(simpleDateFormat.format(simpleDate));

        //格式不一样会抛出ParseException
        Date parse = simpleDateFormat.parse("2022年11月01日 11:19:11 周二 上午");
        System.out.println(parse);

        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        //获取日历的信息: public int get(int field):取日期中的某个字段信息。
        //获取年
        System.out.println(calendar.get(Calendar.YEAR));
        //获取月,从0开始，所以表现需要+1
        System.out.println(calendar.get(Calendar.MONTH) + 1);

        //public void set(int field, int value):修改日历中某个字段的信息
        calendar.set(Calendar.YEAR, 2023);
        System.out.println(calendar);

        //public void add(int field ,int amount):为某个字段增加/减少指定的值
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println(calendar);
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        System.out.println(calendar);

        //public long getTimeInMillis():拿到此刻时间毫秒值
        System.out.println(calendar.getTimeInMillis());
    }
}
