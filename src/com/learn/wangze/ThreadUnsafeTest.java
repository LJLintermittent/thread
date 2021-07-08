package com.learn.wangze;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 * date: 2021/7/7 10:34
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class ThreadUnsafeTest {
    public static void main(String[] args) {
        // 建议在使用list的时候使用带初始容量的构造器，可以让第一次进来以后不需要扩容 grow()
        List<String> list1 = new ArrayList<>(10);
        List<String> list2 = new CopyOnWriteArrayList<>();
//        HashMap<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
