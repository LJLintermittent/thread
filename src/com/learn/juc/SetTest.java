package com.learn.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/9/21 20:43<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class SetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
//        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
