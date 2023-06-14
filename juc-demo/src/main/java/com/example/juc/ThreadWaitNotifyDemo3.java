package com.example.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * *  多线程模板：
 * *  1， 在高内聚 低耦合的前提下  线程   操作（对外暴露的调用方法 ）   资源类 （资源类高内聚，里面有操作的方法，操作的方法考虑并发的安全性）
 * *  2,  线程间通信  判断  干活  通知
 * *  3， 线程间通信要防止虚假唤醒（判断只能用while 不能用if）
 * *  4， 精确唤醒用标志位
 * <p>
 * 多线程之间按顺序调用A 》 B 》 C
 * 要求如下：
 * A打印5次 B打印10次  C打印15此
 * 来10轮
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-04 13:54
 **/
class ShareResource {
    //标志位
    private int number = 1;

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1){
                condition1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2){
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3){
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}


public class ThreadWaitNotifyDemo3 {
    //
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }



}
