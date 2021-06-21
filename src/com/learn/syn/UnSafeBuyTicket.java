package com.learn.syn;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 10:34<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class UnSafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"李佳乐").start();
        new Thread(buyTicket,"徐欣裕").start();
        new Thread(buyTicket,"赵坤").start();


    }

}

class BuyTicket implements Runnable{
    private int TicketNums = 10;
    boolean flag = true;
    @Override
    public synchronized void run() {
        while (flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //同步方法
    private void buy() throws InterruptedException {
        if(TicketNums <= 0){
            flag = false;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+"拿到第"+(TicketNums--)+"张票");
    }
}
