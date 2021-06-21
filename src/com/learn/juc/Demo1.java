package com.learn.juc;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/28 19:09<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class Demo1 {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        try {
//            TimeUnit.DAYS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        TestTicket ticket = new TestTicket();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

class TestTicket {

    Lock lock = new ReentrantLock();

    private int ticket = 50;

    public void sale() {
        lock.lock();
        try {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (ticket--) + "张票，还剩：" + ticket + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}




