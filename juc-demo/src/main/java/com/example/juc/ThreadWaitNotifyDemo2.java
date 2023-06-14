
package com.example.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目： 现在有两个线程 操作一个初始值为0的变量  一个线程+1  一个线程-1  实现交替 来10轮
 * <p>
 * synchronized    wait     notify（notifyAll）
 * lokc            await    signal（signalAll ）
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2021-12-30 16:43
 **/
//资源类
class AirConditioner2 {

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //加
    public void increment() throws InterruptedException {
        lock.lock();    //加锁
        try {
            //1 判断 线程间通信判断必须用while
            while (number != 0) {
                condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "  " + number);

            //3 通知
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


    //减 必须加 synchronized 不然 IllegalMonitorStateException   原因 当前线程不含有当前对象的锁资源的时候，调用obj.wait()，obj.notify()，obj.notifyAll()方法;
    //wait() 方法和 notify() 方法都需要获取到当前线程的共享的对象监视器才能使用，不然会报 IllegalMonitorStateException 异常；也就是必须和 synchronized 同时出现；
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                //1 判断 线程间通信判断必须用while  如果是if  线程走到这里等待  等再次获取锁后直接--  不判断  这样多个线程操作一个方法就会虚假唤醒
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "  " + number);

            //3 通知
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadWaitNotifyDemo2 {

    public static void main(String[] args) {

        AirConditioner2 airConditioner = new AirConditioner2();

        //生产
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        //消费
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        //消费
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        //消费
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}
