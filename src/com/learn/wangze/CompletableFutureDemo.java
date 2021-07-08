package com.learn.wangze;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 * date: 2021/7/8 19:08
 * Package: com.learn.wangze
 *
 * @author 李佳乐
 * @email 18066550996@163.com
 */
@SuppressWarnings("all")
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFutur = new CompletableFuture<>();
        CompletableFuture<Void> future1 = completableFutur.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        future1.get();
        CompletableFuture<Integer> future2 = completableFutur.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 10 / 0;
            return 1024;
        });
        future2.whenComplete((t, u) -> {
            System.out.println("t=" + t);
            System.out.println("u=" + u);
        }).get();
    }
}
