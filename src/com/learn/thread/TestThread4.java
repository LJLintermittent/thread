package com.learn.thread;

/**
 * 一些声明信息
 * Description: <br/>  多个线程操作同一个资源，线程不安全，数据紊乱
 * date: 2020/7/11 14:21<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestThread4 implements Runnable{
    private int ticketNnms = 10;


    @Override
    public void run() {
        while (true){
            if(ticketNnms==0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+(ticketNnms--)+"张票");
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        new Thread(testThread4,"小明").start();
        new Thread(testThread4,"。。。").start();
        new Thread(testThread4,"李佳乐").start();
    }
}
