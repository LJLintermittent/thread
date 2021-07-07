package com.learn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Description:
 * date: 2021/7/4 23:47
 * Package: com.learn
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class date {

    private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        DateFormat dateFormat = threadLocal.get();
        System.out.println(System.currentTimeMillis());
        String s = dateFormat.format(System.currentTimeMillis());
        System.out.println(s);
    }
}
