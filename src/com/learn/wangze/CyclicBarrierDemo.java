package com.learn.wangze;

import java.util.concurrent.CyclicBarrier;

/**
 * Description:
 * date: 2021/7/8 13:00
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class CyclicBarrierDemo {

    private static final int NUM = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, () -> {
            System.out.println("任务完成");
        });
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "号线程完成了任务" + finalI);
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
