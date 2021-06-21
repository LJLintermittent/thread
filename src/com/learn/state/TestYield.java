package com.learn.state;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/11 21:37<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"线程一").start();
        new Thread(myYield,"线程二").start();
    }

}

class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"线程停止执行");
    }
}