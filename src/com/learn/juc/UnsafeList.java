package com.learn.juc;

import org.junit.Test;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/28 21:21<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("1", "2", "3");
        list1.forEach(System.out::println);
        /**
         * vector:线程安全
         */
//        Vector<Object> list  = new Vector<>();
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
//        Collections.synchronizedMap(new HashMap<>());
//        ArrayList<Object> list = new ArrayList<>();
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }


    @Test
    public void testUnsafeList(){
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(UUID.randomUUID().toString().substring(0,5));
        }
        System.out.println(list);

    }
}
