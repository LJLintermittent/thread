package com.learn.wangze;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) throws InterruptedException {
        DateFormat dateFormat = THREAD_LOCAL.get();
        String nowTime = dateFormat.format(System.currentTimeMillis());
        System.out.println(nowTime);
        THREAD_LOCAL.remove();
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

    private final Map<String, Object> cacheMap = new ConcurrentHashMap<>(16);

    public ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Object get(String key) throws InterruptedException {
        Object value = null;
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "号线程正在读取key为" + key + "的值");
            value = cacheMap.get(key);
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
            cacheMap.put(key, value);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + "号线程写完成了");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
