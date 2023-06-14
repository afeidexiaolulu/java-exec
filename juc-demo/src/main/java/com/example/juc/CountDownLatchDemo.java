package com.example.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 * 要求前面几个线程都完成后   当前线程才能绩效运行
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-06 09:53
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //1 创建6个门闩
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "运行完成！！");
                //2 运行完成了减少1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //3在此等待
        countDownLatch.await();

        System.out.println("主线程运行完成！！！");
    }
}
