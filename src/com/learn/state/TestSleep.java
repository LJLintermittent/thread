package com.learn.state;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/11 21:21<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class TestSleep implements Runnable{
    @Override
    public void run() {

    }

    public static void main(String[] args) {
//        TestSleep testSleep = new TestSleep();
//        testSleep.testTimeDown();
//        testTimeDown();
        Date date = new Date(System.currentTimeMillis());
        while (true){
            try {
                Thread.sleep(2000);
                System.out.println(new SimpleDateFormat("HH点mm分ss秒").format(date));
                date = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static void testTimeDown(){
        int i = 10;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i--);
            if(i<=0){
                break;
            }
        }
    }

}

