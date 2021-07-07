package com.learn.syn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * date: 2021/7/6 19:24
 * Package: com.learn.syn
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class SaleTicket {

    public static void main(String[] args) {

        ticket ticket = new ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                int num = ticket.sale();
                if (num != 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了第"
                            + (30 - num) + "张票" + "---还剩" + num + "张票");
                } else if (num == 0) {
                    System.out.println("一号售票员这次并没有卖出票，30张票已经售空");
                }
            }
        }, "一号售票员").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                int num = ticket.sale();
                if (num != 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了第"
                            + (30 - num) + "张票" + "---还剩" + num + "张票");
                } else {
                    System.out.println("二号售票员这次并没有卖出票，30张票已经售空");
                }
            }
        }, "二号售票员").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                int num = ticket.sale();
                if (num != 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了第"
                            + (30 - num) + "张票" + "---还剩" + num + "张票");
                } else {
                    System.out.println("三号售票员这次并没有卖出票，30张票已经售空");
                }
            }
        }, "三号售票员").start();
    }
}

class ticket {

    private int num = 30;

    private final ReentrantLock lock = new ReentrantLock(true);

    /**
     * synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁的发生
     * lock在发生异常时，如果没有主动通过unlock去释放锁，则很可能造成死锁现象
     * 通过lock可以知道有没有成功获取到锁，而synchronized却无法办到
     */
    public int sale() {
        lock.lock();
        try {
            if (num > 0) {
                return num = num - 1;
            } else {
                return 0;
            }
        } finally {
            lock.unlock();
        }
    }
}
