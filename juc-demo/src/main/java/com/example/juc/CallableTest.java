package com.example.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *  Callable用于耗时计算   在当前线程中在开一个线程进行耗时计算  可以获取返回值
 *  1，Callable作为参数传入FutureTask中，FutureTask作为参数传入Thread中
 *  2，调用FutureTask的get()方法可以获得Callable接口中call()方法的返回值
 *  3，调用FutureTask的get()方法当前线程会阻塞，所以call()方法一般放在方法的最后调用
 *  4，同一个FutureTask传入多个Thread中，FutureTask只会调用一次(Callable接口中实现的方法)
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-05 14:31
 **/
class MyCallable implements Callable<Integer>{
    //实现call方法
    @Override
    public Integer call() throws Exception {
        System.out.println("进入call方法内");
        return 666;
    }
}


public class CallableTest {

    public static void main(String[] args)  {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());

        //将futureTask作为参数传给线程中
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();    //B线程里面的FutureTask不会被调用
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("线程" + Thread.currentThread().getName() + "正在运行");

        try {
            Integer integer = futureTask.get();
            System.out.println("返回结果" + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("方法结束");
    }
}
