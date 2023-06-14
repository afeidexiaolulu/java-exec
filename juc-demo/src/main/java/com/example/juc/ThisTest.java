package com.example.juc;

import com.sun.deploy.util.Waiter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试静态 非静态同步方法中wait notify的使用
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-03-20 13:37
 **/
public class ThisTest {

    /**
     * 测试静态同步方法中是否可以使用wait notify
     * wait notify 非静态方法 不可以用在静态方法中
     * ReentrantLock可以使用
     */
    public static synchronized void test1() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //判断 失败
        if(1 > 2){
            condition.await();
            //wait();
        }

    }

}
