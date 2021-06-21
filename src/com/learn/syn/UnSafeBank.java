package com.learn.syn;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 10:47<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(1000,"基金");
        Bank bank = new Bank(account,50,"李佳乐");
        Bank bank1 = new Bank(account,100,"徐欣裕");
        bank1.setPriority(10);
        bank.setPriority(1);

        bank1.start();
        bank.start();

    }
}

class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Bank extends Thread{
    Account account;
    //取了多少钱
    int drawingMoney;
    //手里还有多少钱
    int NowMoney;

    public Bank( Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public  void  run() {
        synchronized (account){
            if(account.money-drawingMoney<0){
                System.out.println(Thread.currentThread().getName()+"钱不够，取不了");
                return;
            }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            account.money = account.money - drawingMoney;
            NowMoney = NowMoney + drawingMoney;
            System.out.println(account.name+"余额为："+account.money);
            System.out.println(this.getName()+"手里的钱为："+NowMoney);
        }
    }
}
