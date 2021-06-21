package com.learn.zhouyang;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/8/2 22:27<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class SaleTicket {
    public static void main(String[] args) {
        new Thread().start();
        new Thread().start();
        new Thread().start();
    }
}

class Ticket{
    private int number = 30;
    public void saleTicket(){
        if (number > 0) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
