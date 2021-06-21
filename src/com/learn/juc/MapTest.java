package com.learn.juc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/9/21 20:59<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class MapTest {
    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }
}
