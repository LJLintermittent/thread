package com.learn.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/9/21 21:21<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        FutureTask task = new FutureTask(thread);
        new Thread(task,"A").start();
        Integer o = (Integer) task.get();
        System.out.println(o);
    }
}
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("call()..");
        return 1024;
    }
}
