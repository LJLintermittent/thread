package com.learn.wangze;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2021/7/7 12:31
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class DeadLock {

    static Object lock1 = new Object();

    static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "已经获得了A锁，想获得B锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + "获得了B锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "已经获得了B锁，想获得A锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + "获得了A锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }, "线程B").start();

    }
}

