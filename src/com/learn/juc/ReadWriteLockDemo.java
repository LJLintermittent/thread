package com.learn.juc;

import java.lang.invoke.MutableCallSite;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/9/21 22:01<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

            for (int i = 1; i <= 5; i++) {
                final int temp = i;
                new Thread(()->{
                    myCache.put(temp + "", temp + "");
                },String.valueOf(i)).start();
            }

        for (int i = 1; i <= 5; i++) {
            final  int temp = i;
            new Thread(()->{
                myCache.get(temp + "");
            },String.valueOf(i)).start();
        }

    }
}

class MyCacheLock{
    private volatile Map<String,Object> map = new HashMap<String,Object>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key , Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }


}

class MyCache{
    private volatile Map<String,Object> map = new HashMap<String,Object>();

    public void put(String key , Object value){
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + "写入ok");

    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取ok");

    }


}
