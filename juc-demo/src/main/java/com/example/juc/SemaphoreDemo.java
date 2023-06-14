package com.example.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * 控制并发数量或者共享资源的互斥操作
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-06 11:29
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        //设置3个控制量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //2 线程获取了资源
                    semaphore.acquire();
                    System.out.println("线程" + Thread.currentThread().getName() + "获取资源");
                    TimeUnit.SECONDS.sleep(3);//睡3秒 模拟占用资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //3 线程释放资源
                    System.out.println("线程" + Thread.currentThread().getName() + "==释放资源");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
