package com.learn.lock;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * date: 2021/7/5 10:47
 * Package: com.learn.lock
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class TestLock2 {

//    private static final Object lock = new Object();

    public static void main(String[] args) {
        doSomething();
//        ReentrantLock lock = new ReentrantLock();
//        //doSomething...
//        lock.lock();
//        try {
//            //doSomething...
//        } finally {
//            lock.unlock();
//        }
    }

    public void test1() {
        ReentrantLock lock = new ReentrantLock();
        //doSomething...
        try {
            //如果这个地方抛出异常，会直接执行finally代码块
            doSomething();
            //无论加锁是否成功，finally代码块都会执行
            lock.lock();
        } finally {
            //unlock方法对未加锁的对象尝试解锁，它会调动AQS的try release方法，抛出非法监视器状态异常
            lock.unlock();
        }
    }

    public void test2() {
        ReentrantLock lock = new ReentrantLock();
        //doSomething...
        boolean isLocked = lock.isLocked();
        if (isLocked) {
            try {
                doSomething();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void doSomething() {
//        AtomicInteger integer = new AtomicInteger();
//        integer.addAndGet(1);
//        integer.getAndIncrement();
        LongAdder x = new LongAdder();

//        int count = 0;
//        if (count == 0 && count < 100) {
//            count++;
//        }
//        System.out.println("xxx");

        for (int i = 0; i < 100; i++) {
            x.increment();
        }
        System.out.println(x.longValue());
    }

}
