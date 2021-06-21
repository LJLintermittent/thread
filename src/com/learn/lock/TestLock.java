package com.learn.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 12:57<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestLock {
    public static void main(String[] args) {
        Lock testLock = new Lock();
        new Thread(testLock).start();
        new Thread(testLock).start();
        new Thread(testLock).start();
    }


}
class Lock implements Runnable{
    int ticketNums = 10;
    private final ReentrantLock lock =  new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if(ticketNums>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
