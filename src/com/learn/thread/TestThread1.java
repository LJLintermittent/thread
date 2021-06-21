package com.learn.thread;

/**
 * 一.继承thread类
 * Description: <br/>
 * date: 2020/7/11 13:34<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("线程Thread1:"+i);
        }
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        testThread1.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("主线程main："+i);
        }
    }

}
