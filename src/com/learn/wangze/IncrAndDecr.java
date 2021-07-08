package com.learn.wangze;

/**
 * Description:
 * date: 2021/7/6 20:18
 * Package: com.learn.syn
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class IncrAndDecr {

    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    share.add3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    share.add2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    share.add1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}

class Share {
    private int num = 0;

    // wait方法存在虚假唤醒问题，应该始终放在while循环中使用
    public synchronized void add3() throws InterruptedException {
        while (num % 3 != 0) {
            this.wait();
        }
        num = num + 1;
        System.out.println(Thread.currentThread().getName() + "将num值改为了:" + num);
        this.notifyAll();
    }

    public synchronized void add2() throws InterruptedException {
        while (num % 3 != 1) {
            this.wait();
        }
        num = num + 1;
        System.out.println(Thread.currentThread().getName() + "将num值改为了:" + num);
        this.notifyAll();
    }

    public synchronized void add1() throws InterruptedException {
        while (num % 3 != 2) {
            this.wait();
        }
        num = num + 1;
        System.out.println(Thread.currentThread().getName() + "将num值改为了:" + num);
        this.notifyAll();
    }
}
