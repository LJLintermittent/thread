package com.learn.wangze;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 * date: 2021/7/8 10:36
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
public class CallAndRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread3(), "Thread类线程").start();
        new Thread(new MyThread1(), "Runnable线程").start();
        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName());
            return 100;
        });
        new Thread(futureTask1, "FutureTask创建Callable线程").start();
        while (!futureTask1.isDone()) {
            System.out.println("futureTask1计算还未完成..");
        }
        System.out.println(futureTask1.get());
        System.out.println(futureTask1.get());
        System.out.println(Thread.currentThread().getName() + " over");

    }
}

class MyThread1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1;
    }
}

class MyThread3 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
