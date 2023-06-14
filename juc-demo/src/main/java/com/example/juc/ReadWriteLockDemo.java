package com.example.juc;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 读操作不加锁  写操作加锁
 * 写写互斥  只能有一个线程写 写的时候其他线程不可以读写
 * 读写互斥  读的时候写不能操作可以读
 *
 * ===只要有写操作就要加锁
 * 写可能是多步的  写操作要求原子性的情况下  加写锁 这样可以保证原子性
 * 读可以共享
 *
 * 那么如果使用readwritelock 锁的话，当拥有该对象的某个线程在进行读取时，其他线程也可以进行读取，但是不能写入操作（例如，大家都在上厕所的时候，保洁不能打扫厕所）。
 * 当该线程进行写入操作时，其他线程不可以读取和写入（例如，保洁打扫厕所的时候，任何人不能入内）。所以readwritelock 锁，只有在进行写操作的时候，才会阻断其他线程，即线程同步，
 * 当进行读取操作的时候，其他读操作的线程可以进行并发进行，则写操作等待。
 *
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-06 15:21
 **/
class Mycache {

    private HashMap map = new HashMap();

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(Object k, Object v) throws InterruptedException {
        //加写锁
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "线程开始写入");
            Thread.sleep(300);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "线程写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(Object k) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "线程开始读取");
            Thread.sleep(300);
            map.get(k);
            System.out.println(Thread.currentThread().getName() + "线程读取完成");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}


public class ReadWriteLockDemo {

    //模拟6个线程读 6个线程写
    public static void main(String[] args) {
        Mycache mycache = new Mycache();

        for (int i = 0; i < 6; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    mycache.put(tempInt, UUID.randomUUID());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 6; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    mycache.get(tempInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

}
