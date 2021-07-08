package com.learn.wangze;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 * date: 2021/7/8 13:48
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        // 写线程
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    myCache.put(finalI + "", finalI + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        TimeUnit.MICROSECONDS.sleep(300);

        //读线程
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    myCache.get(finalI + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 读锁，共享锁
 * 写锁，独占锁
 */
class MyCache {

    private final Map<String, Object> map = new HashMap<>(16);

    public ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Object get(String key) throws InterruptedException {
        Object value = null;
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "号线程正在读取key为" + key + "的值");
            value = map.get(key);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + "号线程读取完成了，value为" + value);
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    public void put(String key, Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "号线称正在写内容");
            map.put(key, value);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + "号线程写完成了");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
