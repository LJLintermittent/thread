package com.learn.syn;

import java.util.ArrayList;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 11:07<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class UnSafeList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            new Thread(()->{
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }

}
