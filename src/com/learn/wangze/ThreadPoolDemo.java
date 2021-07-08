package com.learn.wangze;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * date: 2021/7/8 15:59
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class ThreadPoolDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        /**
         * int corePoolSize,
         * int maximumPoolSize,
         * long keepAliveTime,
         * TimeUnit unit,
         * BlockingQueue<Runnable> workQueue,
         * ThreadFactory threadFactory,
         * RejectedExecutionHandler handler
         *
         *拒绝策略：
         * AbortPolicy：抛出异常，系统终止
         * CallerRunsPolicy：调用者运行
         * DiscardPolicy：新来的任务直接抛弃，既不抛出异常，也不进行处理
         * DiscardOldestPolicy：抛弃队列中等待最久的任务，将新来的任务加入到等待队列
         */
        ExecutorService myThreadPool = new ThreadPoolExecutor(
                3,
                10,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            //30个人来买票（进入售票厅这个池子）
            for (int i = 1; i <= 30; i++) {
                //池子分配线程（服务窗口来处理业务）
                myThreadPool.execute(() -> {
                    ticket.sale();
                });
            }
        } finally {
            myThreadPool.shutdown();
        }

    }
}

class Ticket {

    private static int num = 10;

    private static final int flag = 11;

    private final ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "号线程卖出了第" + (flag - num) + "票，还剩"
                        + (num - 1) + "张票");
                num = num - 1;
            }
            if (num == 0) {
                System.out.println(Thread.currentThread().getName() + "号售票员这里票已经卖光，没有卖出");
            }
        } finally {
            lock.unlock();
        }
    }
}
