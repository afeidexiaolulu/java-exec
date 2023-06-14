package com.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *java 线程状态：   新建  运行 阻塞  等待  有时等待  死亡
 * wait 放开手去睡
 * sleep 抓着锁睡
 *
 *
 * 进程：运行中的程序  eg:QQ  资源分配的单位
 * 线程：一个进程中可以包含多个线程  例如：QQ可以和多人聊天  调度和执行的单位
 *
 * 并发：并发的关键是你有处理多个任务的能力，不一定要同时   你吃饭吃到一半，电话来了，你停了下来接了电话，接完后继续吃饭，这说明你支持并发  这个‘你’是资源，
 * 电话和吃饭看作线程，即从线程角度就是多个线程争夺一个资源。
 * 并行：并行的关键是你有同时处理多个任务的能力  你吃饭吃到一半，电话来了，你一边打电话一边吃饭，这说明你支持并行，强调 多个线程同时进行。
 *
 *
 *
 * ==lambda 表达式
 * 1，接口不可以实例化   匿名内部类写法中 new 接口是实例化接口的隐藏实现类
 * 2，函数式接口可以简化为Lambda表达式（只有一个抽象方法的接口）  复制小括号  写死右箭头   落地大括号
 * 3， 接口里面可以有静态方法  接口里面可以有 default方法
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2021-12-29 16:29
 *
 *  题目： 3个售票员 卖出   30张票
 *
 *  多线程模板：
 *  1， 在高内聚 低耦合的前提下  线程   操作（对外暴露的调用方法 ）   资源类 （资源类高内聚，里面有操作的方法，操作的方法考虑并发的安全性）
 *  2,  线程间通信  判断  干活  通知
 *  3， 线程间通信要防止虚假唤醒（判断只能用while 不能用if）
 *
 **/

class Ticket{

    private int ticketNum = 30;

    private Lock lock = new ReentrantLock();//可重入锁

    //操作方法
    public void saleTicket(){
        lock.lock();//上锁
        try {
            if(ticketNum > 0){
                System.out.println("现在" + Thread.currentThread().getName() + "卖第:" + ticketNum-- + "张票,还剩:" + ticketNum +"张票");
            }
        } finally {
            lock.unlock();//必须解锁
        }
    }
}

public class SaleTicket1 {

    //模拟3个售票员卖票
    public static void main(String[] args) {
        //1 创建资源类
        Ticket ticket = new Ticket();

        //2 创建线程 操作资源类
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }}, "A").start();

        new Thread(() -> { for (int i = 0; i < 40; i++) {
            ticket.saleTicket();
        }}, "B").start();

        new Thread(() -> { for (int i = 0; i < 40; i++) {
            ticket.saleTicket();
        }}, "C").start();
    }

}
