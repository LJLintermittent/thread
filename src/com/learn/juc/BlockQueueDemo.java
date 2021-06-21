package com.learn.juc;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/9/21 23:10<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class BlockQueueDemo {
    public static void main(String[] args) {

    }

    /**
     * 抛出异常
     */
    @Test
    public void test1(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
//        blockingQueue.add("d");
        System.out.println("============");
        //查看队首元素是谁
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
    }

    /**
     *不抛出异常,有返回值
     */
    @Test
    public void test2(){
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d");
        System.out.println("==============");

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());


    }

    /**
     *等待阻塞(一直阻塞)
     */
    @Test
    public void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    /**
     *等待阻塞(等待超时)
     */
    @Test
    public void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a",2, TimeUnit.SECONDS);
        blockingQueue.offer("b",2, TimeUnit.SECONDS);
        blockingQueue.offer("c",2, TimeUnit.SECONDS);
//        blockingQueue.offer("d",2, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
//        blockingQueue.poll(2,TimeUnit.SECONDS);

    }

}


