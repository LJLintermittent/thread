package com.learn.thread;

/**
 * 实现runnable接口
 * Description: <br/>
 * date: 2020/7/11 14:14<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestThread3 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("副线程："+i);
        }
    }

    public static void main(String[] args) {
        TestThread3 testThread3 = new TestThread3();
//        Thread thread = new Thread(testThread3);
//        thread.start();
        new Thread(testThread3).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("主线程："+i);
        }
    }
}
