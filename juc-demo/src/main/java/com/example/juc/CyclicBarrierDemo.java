package com.example.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 集齐7颗龙珠 召唤神龙
 *
 * 1,在构造方法中传递runable接口，线程中调用await()方法后 先执行runable中实现的方法在执行await()后面的代码
 * 2,和CountDownLatch区别
 *  1，CountDownLatch是两组线程，第一组负责计数器减一，第二组是阻塞线程，当第一组线程将计数器减到0时，
 *  第二组线程才开始执行，放行是由第三方控制；
 *  2，CyclicBarrier是只有一组线程，《只有当所有线程到达拦截点的时候，才会继续往下执行》，放行是由一组线程本身控制。
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-06 09:59
 **/
public class  CyclicBarrierDemo {

    public static void main(String[] args) {
        //1 创建循环栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("集齐7颗龙珠，召唤神龙"); });

        for (int i = 0; i < 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println("集齐到第：" + tempInt + "颗龙珠") ;
                try {
                    //2 等待
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "又开始运行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(tempInt)).start();
        }
    }
}
