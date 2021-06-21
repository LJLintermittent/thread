package com.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 13:39<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class test {
    public static void main(String[] args) {
        new MyThread1().start();
        MyThread2 myThread2 = new MyThread2();
        new Thread(myThread2).start();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3());
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("MyThread2");

    }
}

class MyThread3 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 100;
    }
}
