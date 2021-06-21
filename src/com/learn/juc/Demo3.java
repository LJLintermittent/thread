package com.learn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/28 20:18<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class Demo3 {
    public static void main(String[] args) {

        data2 data2 = new data2();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data2.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data2.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data2.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data2.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }

}

class data2 {
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + ">>>" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + ">>>" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
