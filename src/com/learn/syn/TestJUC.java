package com.learn.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/12 11:27<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());

    }
}
