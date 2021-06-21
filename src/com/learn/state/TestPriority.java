package com.learn.state;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/11 22:06<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"--->"+Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority);
        Thread thread2 = new Thread(myPriority);
        Thread thread3 = new Thread(myPriority);
        Thread thread4 = new Thread(myPriority);
        Thread thread5 = new Thread(myPriority);
        Thread thread6 = new Thread(myPriority);

        thread1.setPriority(1);
        thread1.start();

        thread2.setPriority(2);
        thread2.start();

        thread3.setPriority(3);
        thread3.start();

        thread4.setPriority(5);
        thread4.start();

        thread5.setPriority(7);
        thread5.start();

        thread6.setPriority(10);
        thread6.start();


    }

}

class MyPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--->"+Thread.currentThread().getPriority());
    }
}
