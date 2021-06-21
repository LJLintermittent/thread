package com.learn.juc;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/9/21 21:30<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class countTest {
    public static void main(String[] args){

        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到位置");
//                    Thread.sleep(2000);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开位置");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }

    @Test
    public void testCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "go out");
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("close!!!");
    }

    @Test
    public void testCyclicBarrier(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("success!!");
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +">>>>>>"+ temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Test
    public void testSemaphore(){
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到位置");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "离开位置");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }


}

