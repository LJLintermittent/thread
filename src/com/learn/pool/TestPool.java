package com.learn.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 13:29<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());

        pool.shutdown();
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
