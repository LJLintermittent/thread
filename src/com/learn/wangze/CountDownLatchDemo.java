package com.learn.wangze;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * Description:
 * date: 2021/7/8 12:35
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class CountDownLatchDemo {

    private static int NUM = 10;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 1; i <= NUM; i++) {
            int finalI = i;
            new Thread(new FutureTask<String>(() -> {
                System.out.println(Thread.currentThread().getName() + "号线程离开");
                countDownLatch.countDown();
                return String.valueOf(finalI);
            }), String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "线程离开");
    }
}
