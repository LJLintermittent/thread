package com.learn.thread;


/**
 * 模拟龟兔赛跑
 * Description: <br/>
 * date: 2020/7/11 14:31<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class Race implements Runnable{
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(Thread.currentThread().getName().equals("乌龟") && i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = gameOver(i);
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"米");
        }
    }
    public boolean gameOver(int len){
        if(winner!=null){
            return true;
        }else{
            if(len ==100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }

}
