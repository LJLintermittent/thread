package com.learn.wangze;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2021/7/8 15:17
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        //添加
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        //取出
        System.out.println(blockingQueue.size());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //检查
//        System.out.println(blockingQueue.element());

        //添加
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //取出
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //检查
        System.out.println(blockingQueue.peek());


        //添加
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");

        //取出
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        //延时阻塞，当阻塞的时间超过传入的时间时，取消阻塞，返回结果
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("c", 3L, TimeUnit.SECONDS));


    }

}
