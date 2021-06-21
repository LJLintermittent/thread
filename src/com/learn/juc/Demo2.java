package com.learn.juc;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/28 20:18<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class Demo2 {
    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }

}

class Data {
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + ">>>" + num);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + ">>>" + num);
        this.notifyAll();
    }

}
