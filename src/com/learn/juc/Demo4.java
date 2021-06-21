package com.learn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/28 20:53<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class Demo4 {
    public static void main(String[] args) {
        Data3 date = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }


}

class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num = 1;
    public void printA() throws InterruptedException {
        lock.lock();
        try {
            while (num!=1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"》》AAA");
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB() throws InterruptedException {
        lock.lock();
        try {
            while (num!=2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"》》BBB");
            num = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC() throws InterruptedException {
        lock.lock();
        try {
            while (num!=3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"》》CCC");
            num = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
