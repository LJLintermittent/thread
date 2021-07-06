package com.learn.wangze;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * date: 2021/7/6 21:19
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class IncrAndDecrByLock {

    public static void main(String[] args) {

        share share = new share();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                share.add1();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                share.add2();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                share.add3();
            }
        }, "C").start();
    }


}
@SuppressWarnings("all")
class share {

    private int num = 0;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void add1() {
        lock.lock();
//        error();
        try {
            while (num % 3 != 0) {
                condition.await();
            }
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + "把num更新为：" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void add2() {
        lock.lock();
        try {
            while (num % 3 != 1) {
                condition.await();
            }
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + "把num更新为：" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void add3() {
        lock.lock();
        try {
            while (num % 3 != 2) {
                condition.await();
            }
            num = num + 1;
            System.out.println(Thread.currentThread().getName() + "把num更新为：" + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int error() {
        try {
            int i = 10 / 0;
            return i;
        }catch (Exception e){
            System.out.println("错误");
        }
        return 0;
    }
}
