package com.example.juc;

import javax.lang.model.element.VariableElement;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 不安全示例 list不安全
 * <p>
 * 1,示例： 使用ArrayList 出现ConcurrentModificationException 异常
 * 原因： add方法分两步 扩容 写入  多线程操作 刚刚扩容还没有写入就读 此时读取结果每次都不一致
 * <p>
 * 2，解决方法：
 * 1，使用 Vector   add()方法加了synchronized锁，效率低 不使用
 * 2，使用 Collections.synchronizedList(new ArrayList<>())   效率仍然低  不建议
 * 3，使用CopyOnWriteArrayList 写时复制类  建议
 *
 * @program: java-exec-juc
 * @packagename: com.exec.juc
 * @author: lwj
 * @date: 2022-01-05 09:51
 **/
public class NotSafeDemo {

    public static void main(String[] args) {

        //List list = new ArrayList();
        //List list = new Vector();
        //List list = Collections.synchronizedList(new ArrayList<>());
        List list = new CopyOnWriteArrayList(); //建议

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().subSequence(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
