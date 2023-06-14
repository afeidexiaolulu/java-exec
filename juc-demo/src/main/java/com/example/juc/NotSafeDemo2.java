package com.example.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 不安全示例 set不安全
 * <p>
 * 1,示例： 使用ArrayList 出现ConcurrentModificationException 异常
 * 原因： add方法分两步 扩容 写入  多线程操作 刚刚扩容还没有写入就读 此时读取结果每次都不一致
 * <p>
 * 2，解决方法：
 * 2，使用 Collections.synchronizedSet(new HashSet<>())   效率仍然低  不建议
 * 3，使用CopyOnWriteArraySet 写时复制类  建议
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-05 09:51
 **/
public class NotSafeDemo2 {

    public static void main(String[] args) {
        //Set set = Collections.synchronizedSet(new HashSet<>());
        Set set = new CopyOnWriteArraySet<>(); //建议

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().subSequence(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
