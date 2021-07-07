package com.learn.wangze;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2021/7/7 10:54
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class EightLock {

    /**
     * synchronized 八锁演示
     * 对于普通同步方法，锁是当前实例对象(this对象),如果对象一样，那么按照顺序执行就ok
     * 对于静态同步方法，锁是当前类的Class对象，如果一个锁是静态，一个是非静态，那么两个方法锁的对象不一样，互不干扰
     * 对于同步代码块，锁是括号里配置的对象
     */
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone1.sendEms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone1.sendEmail();
        }, "B").start();
    }

}

class Phone {

    public synchronized void sendEms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("sendEms");
    }

    public static synchronized void sendEmail() {
        System.out.println("sendEmail");
    }

    public void getCall() {
        System.out.println("hello");
    }

}
