package org.example.commonapis.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 从Java 8开始，java.time包提供了新的日期和时间API，主要涉及的类型有:
 * LocalDate:不包含具体时间的日期。
 * LocalTime:不含日期的时间。
 * LocalDateTime:包含了日期及时间。
 * Instant:代表的是时间戳。
 * DateTimeFormatter用于做时间的格式化和解析的
 * Duration:用于计算两个“时间”间隔
 * Period:用于计算两个“日期”间隔
 * ChronoUnit:时间计算工具类
 * 1、新增的API严格区分了时刻、本地日期、本地时间，并且，对日期和时间进行运算更加方便。
 * 2、新API的类型几乎全部是不变类型（和String的使用类似)，可以放心使用不必担心被修改。
 */
public class DateCommonJdk8 {
    public static void main(String[] args) {

    }
}
