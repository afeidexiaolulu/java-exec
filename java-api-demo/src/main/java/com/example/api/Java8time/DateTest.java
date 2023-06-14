package com.example.api.Java8time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @program: java-exec
 * @packagename: com.example.Java8.time
 * @author: lwj
 * @date: 2023-06-12 14:06
 *
 *
 * 此配置类用来测试java8的时间api
 * java.time包下的所有类都是不可变类型而且线程安全。
 * LocalDate, 本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
 * LocalDateTime, 组合了日期和时间，但不包含时差和时区信息。
 * LocalTime 本地时间，不包含日期。
 * ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
 *
 **/
public class DateTest {

    //测试时间api
    public static void main(String[] args) {
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        Date date = new Date();
        System.out.println("Date:" + date);

        //获取当前日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        //获取当前时间
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);


        //静态方法，根据指定日期/时间创建对象
        LocalTime localTime1 = LocalTime.of(12, 12, 12);
        System.out.println(localTime1);


        //获取月份天数(1-31)/获取年份天数(1~366)
        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println("获取月份天数" + dayOfMonth);
        int dayOfMonth1 = localDateTime.getDayOfMonth();
        System.out.println("获取月份天数" + dayOfMonth1);





    }


}
