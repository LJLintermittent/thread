package com.learn.state;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/11 21:42<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("插队线程："+i);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();
        for (int i = 0; i < 1000; i++) {
            if(i==10){
                thread.join();
            }
            System.out.println("main线程："+i);
        }

    }
}

