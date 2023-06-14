package com.example.juc;

/**
 * 8锁问题
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-04 14:26
 *
 * 笔记：
 *  一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *  其他线程调用带有synchronized的方法只能等待，因为当前锁对象为this 即对象本身
 *
 *  static修饰的字段  方法 从属于类，加锁是锁定的Class
 *
 **/
class Phone{

}


public class Lock8 {
}
